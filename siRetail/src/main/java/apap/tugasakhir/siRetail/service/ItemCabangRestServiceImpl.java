package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.ItemCabangModel;
import apap.tugasakhir.siRetail.model.Kategori;
import apap.tugasakhir.siRetail.repository.ItemCabangDB;
import apap.tugasakhir.siRetail.rest.ItemCabangDetail;
import apap.tugasakhir.siRetail.rest.ItemDetail;
import apap.tugasakhir.siRetail.rest.ResponseReader;
import apap.tugasakhir.siRetail.rest.Setting;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.JsonNode;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemCabangRestServiceImpl implements ItemCabangRestService {
    private final WebClient webClient;
    private final WebClient webClientItem;

    @Autowired
    private ItemCabangDB itemCabangDB;

    public ItemCabangRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://0a5f4b1b-c14b-48f4-ac59-a2786792046e.mock.pstmn.io").build();
        this.webClientItem = webClientBuilder.baseUrl("https://si-item.herokuapp.com").build();
    }

    @Override
    public ItemCabangDetail updateStok(ItemCabangModel itemCabangUpdate) {

        // ItemCabangModel itemCabang = getItemCabangById(idItemCabang);
        ItemCabangDetail itemDetail = new ItemCabangDetail();

        itemDetail.setIdCabang(itemCabangUpdate.getCabang().getIdCabang());
        itemDetail.setUuidItem(itemCabangUpdate.getUuidItem());
        itemDetail.setTambahanStok(itemCabangUpdate.getStok());

        String namaKategori = itemCabangUpdate.getKategori().replaceAll(" & ", "_DAN_").replace(" ", "_");
        itemDetail.setIdKategori(Kategori.valueOf(namaKategori).ordinal() + 1);
        ItemCabangDetail result;
        ItemCabangDetail response = this.webClient.post().uri("/api/request-update-item/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(itemDetail), ItemCabangDetail.class)
                .retrieve()
                .bodyToMono(ItemCabangDetail.class).block();
                // .subscribe(
                //         value -> result = value,
                //         error -> error.printStackTrace(),
                //         () -> System.out.println("completed without a value"));
        return response;
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
        Optional<ItemCabangModel> bioskop = itemCabangDB.findByUuidItem(uuidItem);
        if (bioskop.isPresent()) {
            return bioskop.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<ItemDetail> getAllItem() {
        List<ItemDetail> itemCabangList = new ArrayList<>();

        ResponseReader response = this.webClientItem.get().uri("/api/item")
                // ResponseReader response = this.webClientItem.get().uri(fitur12Url)
                .retrieve()
                .bodyToMono(ResponseReader.class).block();

        for (JsonNode item : response.getResult()) {
            String uuid = item.get("uuid").textValue();
            String nama = item.get("nama").textValue();
            Integer harga = item.get("harga").intValue();
            Integer stok = item.get("stok").intValue();
            String kategori = item.get("kategori").textValue();
            itemCabangList.add(new ItemDetail(uuid, nama, harga, stok, kategori));
        }

        return itemCabangList;

    }
}
