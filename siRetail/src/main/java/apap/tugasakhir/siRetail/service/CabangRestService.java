package apap.tugasakhir.siRetail.service;
import apap.tugasakhir.siRetail.model.CabangModel;

import java.util.HashMap;
import java.util.List;

public interface CabangRestService {
    HashMap<String,String> retrieveListAlamatCabang();
    CabangModel createCabang(CabangModel cabang);
    List<CabangModel> retrieveListCabang();
}
