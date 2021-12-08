package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.model.ItemCabangModel;
import apap.tugasakhir.siRetail.repository.CabangDB;
import apap.tugasakhir.siRetail.repository.ItemCabangDB;
import apap.tugasakhir.siRetail.rest.KuponDetail;
import apap.tugasakhir.siRetail.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    // @Override
    // public ArrayList<HashMap<String, String>> retrieveListAlamatCabang(){
    //     ArrayList<HashMap<String,String>> result = new ArrayList<>();
    //     List<CabangModel> listCabang = cabangDB.findAll();

    //     for (CabangModel cabang : listCabang){
    //         HashMap<String,String> mapAlamatCabang = new HashMap<>();
    //         String idCabang = String.valueOf(cabang.getIdCabang());
    //         String alamat = cabang.getAlamat();
    //         mapAlamatCabang.put("id",idCabang);
    //         mapAlamatCabang.put("alamat",alamat);
    //         result.add(mapAlamatCabang);
    //     }
    //     return result;
    // }

    @Override
    public CabangModel createCabang(CabangModel cabang) {
        cabang.setStatus(0);
        return cabangDB.save(cabang);
    }

    @Override
    public List<CabangModel> retrieveListCabang() {
        return cabangDB.findAll();
    }

    @Override
    public Mono<KuponDetail> listCoupon(Long idItemCabang){

        // ItemCabangModel item = itemCabangDB.findByIdItemCabang(idItemCabang);
        // return this.webClient.get().uri("?idItemCabang=" + item.getIdItemCabang())
        //         .retrieve()
        //         .bodyToMono(KuponDetail.class);
        return null;

    }
}
