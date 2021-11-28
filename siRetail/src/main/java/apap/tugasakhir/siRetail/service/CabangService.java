package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.CabangModel;

import java.util.List;

public interface CabangService {
    List<CabangModel> getListCabang();

    void addCabang(CabangModel cabang);

    void updateCabang(CabangModel cabang);

    CabangModel getCabangByIdCabang(Long idCabang);
}
