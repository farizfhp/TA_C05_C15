package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.ItemCabangModel;
import apap.tugasakhir.siRetail.model.Kategori;
import apap.tugasakhir.siRetail.repository.ItemCabangDB;
import apap.tugasakhir.siRetail.rest.ItemCabangDetail;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemCabangRestServiceImpl implements ItemCabangRestService {
    private final WebClient webClient;

    @Autowired
    private ItemCabangDB itemCabangDB;

    public ItemCabangRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://0a5f4b1b-c14b-48f4-ac59-a2786792046e.mock.pstmn.io").build();
    }

    @Override
    public Mono<ItemCabangDetail> updateStok(ItemCabangModel itemCabangUpdate) {

        // ItemCabangModel itemCabang = getItemCabangById(idItemCabang);
        ItemCabangDetail itemDetail = new ItemCabangDetail();

        itemDetail.setIdCabang(itemCabangUpdate.getCabang().getIdCabang());
        itemDetail.setUuidItem(itemCabangUpdate.getUuidItem());
        itemDetail.setTambahanStok(itemCabangUpdate.getStok());

        String namaKategori = itemCabangUpdate.getKategori().replaceAll(" & ", "_DAN_").replace(" ", "_");
        itemDetail.setIdKategori(Kategori.valueOf(namaKategori).ordinal() + 1);

        return this.webClient.post().uri("/api/request-update-item/add" + itemCabangUpdate.getUuidItem())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(itemDetail), ItemCabangDetail.class)
                .retrieve()
                .bodyToMono(ItemCabangDetail.class);
    }

    @Override
    public ItemCabangModel getItemCabangById(Long idItemCabang) {
        Optional<ItemCabangModel> bioskop = itemCabangDB.findByIdItemCabang(idItemCabang);
        if (bioskop.isPresent()) {
            return bioskop.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public ItemCabangModel getItemCabangByUuid(String uuidItem) {
        Optional<ItemCabangModel> bioskop = itemCabangDB.findByUuidItemCabang(uuidItem);
        if (bioskop.isPresent()) {
            return bioskop.get();
        } else {
            throw new NoSuchElementException();
        }
    }
}
