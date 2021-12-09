package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.repository.CabangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugasakhir.siRetail.model.ItemCabangModel;
import apap.tugasakhir.siRetail.repository.CabangDB;
import apap.tugasakhir.siRetail.repository.ItemCabangDB;
import apap.tugasakhir.siRetail.rest.ItemDetail;
import apap.tugasakhir.siRetail.rest.KuponDetail;
import apap.tugasakhir.siRetail.rest.ResponseReader;
import apap.tugasakhir.siRetail.rest.Setting;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.*;

@Service
@Transactional
public class CabangRestServiceImpl implements CabangRestService{
    private final WebClient webClient;

    @Autowired
    private CabangDB cabangDB;

    @Autowired
    private ItemCabangDB itemCabangDB;

    public CabangRestServiceImpl(WebClient.Builder webclientBuilder){
        this.webClient = webclientBuilder.baseUrl(Setting.fitur12Url).build();
    }

    @Override
    public ArrayList<HashMap<String, String>> retrieveListAlamatCabang(){
        ArrayList<HashMap<String,String>> result = new ArrayList<>();
        List<CabangModel> listCabang = cabangDB.findAll();

        for (CabangModel cabang : listCabang){
            HashMap<String,String> mapAlamatCabang = new HashMap<>();
            String idCabang = String.valueOf(cabang.getIdCabang());
            String alamat = cabang.getAlamat();
            mapAlamatCabang.put("id",idCabang);
            mapAlamatCabang.put("alamat",alamat);
            result.add(mapAlamatCabang);
        }
        return result;
    }

    @Override
    public CabangModel createCabang(CabangModel cabang) {
        cabang.setStatus(0);
        return cabangDB.save(cabang);
    }

    @Override
    public List<CabangModel> retrieveListCabang() {
        return cabangDB.findAll();
    }

//    @Override
//    public List<KuponDetail> listCoupon(){
//        Mono<List<KuponDetail>> response = webClient.get().uri("rest/coupon/neededresponse")
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<List<KuponDetail>>() {});
//        List<KuponDetail> listCoupon = response.block();
//
//        return new ArrayList<>(listCoupon);
//    }

    @Override
    public List<KuponDetail> listCoupon(){
        List<KuponDetail> result = new ArrayList<>();
        ResponseReader response = this.webClient.get().uri("rest/coupon/neededresponse")
                .retrieve()
                .bodyToMono(ResponseReader.class).block();

        for (JsonNode kupon: response.getResult()){
            String idCoupon = kupon.get("id-coupon").textValue();
            String couponCode = kupon.get("coupon-code").textValue();
            String couponName = kupon.get("coupon-name").textValue();
            String discountAmount = kupon.get("discount-amount").textValue();
            String expiryDate = kupon.get("expiry-date").textValue();
            result.add(new KuponDetail(idCoupon,couponCode,couponName,discountAmount,expiryDate));
        }
        return result;
    }

}
