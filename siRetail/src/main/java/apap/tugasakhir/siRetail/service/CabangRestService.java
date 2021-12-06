package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.CabangModel;

import java.util.List;

public interface CabangRestService {
    CabangModel createCabang(CabangModel cabang);
    List<CabangModel> retrieveListCabang();

}
