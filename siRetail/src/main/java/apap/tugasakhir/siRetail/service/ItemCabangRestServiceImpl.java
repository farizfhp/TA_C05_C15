package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.model.ItemCabangModel;
import apap.tugasakhir.siRetail.model.Kategori;
import apap.tugasakhir.siRetail.repository.CabangDB;
import apap.tugasakhir.siRetail.repository.ItemCabangDB;
import apap.tugasakhir.siRetail.rest.ItemCabangDetail;
import apap.tugasakhir.siRetail.rest.ItemDetail;
import apap.tugasakhir.siRetail.rest.ResponseReader;
import apap.tugasakhir.siRetail.rest.Setting;
import org.springframework.beans.factory.annotation.Qualifier;
import reactor.core.Disposable;

import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
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

    @Autowired
    private CabangDB cabangDB;

    @Qualifier("cabangServiceImpl")
    @Autowired
    private CabangService cabangService;

    public ItemCabangRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://24beed10-c76c-4236-a73b-3bb5f713add6.mock.pstmn.io").build();
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
        ResponseReader response = this.webClient.post().uri("/api/request-update-item/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(itemDetail), ItemCabangDetail.class)
                .retrieve()
                .bodyToMono(ResponseReader.class).block();
        System.out.println(response);
        ItemCabangDetail item = new ItemCabangDetail();
        JsonNode result = response.getResult();
        item.setTambahanStok(result.get("tambahanStok").intValue());
        item.setUuidItem(result.get("idItem").textValue());
        item.setIdKategori(result.get("idKategori").intValue());
        return item;
    }

    @Override
    public List<ItemDetail> getAllItem() {
        List<ItemDetail> itemCabangList = new ArrayList<>();

        ResponseReader response = this.webClientItem.get().uri("/api/item")
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

    @Override
    public ResponseReader updateSiItem(ItemCabangModel item, Integer stokUpdate) {
        ItemDetail itemDetail = new ItemDetail();

        itemDetail.setStok(stokUpdate);
        System.out.println("baru mau masuk");
        ResponseReader response = this.webClientItem.put().uri("/api/item/" + item.getUuidItem())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(itemDetail), ItemCabangDetail.class)
                .retrieve()
                .bodyToMono(ResponseReader.class).block();
        System.out.println("dah masuk");
        return response;
    }

    @Override
    public Integer getItemStok(String uuid) {

        ResponseReader response = this.webClientItem.get().uri("/api/item/" + uuid)
                .retrieve()
                .bodyToMono(ResponseReader.class).block();

        return response.getResult().get("stok").intValue();

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
    public void applyCoupon(Long idItemCabang, Integer idCoupon, Float discountAmount) {
        ItemCabangModel item = getItemCabangById(idItemCabang);
        item.setIdPromo(idCoupon);
        Integer harga = (int) ((1 - discountAmount) * item.getHarga());
        item.setHarga(harga);
        itemCabangDB.save(item);
    }
}
