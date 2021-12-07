package apap.tugasakhir.siRetail.service;
import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.rest.KuponDetail;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface CabangRestService {
    ArrayList<HashMap<String,String>> retrieveListAlamatCabang();
    CabangModel createCabang(CabangModel cabang);
    List<CabangModel> retrieveListCabang();
    Mono<KuponDetail> listCoupon(Long idItemCabang);
}
