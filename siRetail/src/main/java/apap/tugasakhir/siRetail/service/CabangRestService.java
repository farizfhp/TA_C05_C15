package apap.tugasakhir.siRetail.service;
import apap.tugasakhir.siRetail.model.CabangModel;

import java.util.List;

public interface CabangRestService {
    List<String> retrieveListAlamatCabang();
    CabangModel createCabang(CabangModel cabang);
}
