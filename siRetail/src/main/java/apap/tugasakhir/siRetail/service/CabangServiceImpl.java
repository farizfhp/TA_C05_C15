package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.repository.CabangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class CabangServiceImpl implements CabangService{

    @Autowired
    CabangDB cabangDB;

    @Override
    public List<CabangModel> getListCabang() {
        return cabangDB.findAll();
    }

    @Override
    public void addCabang(CabangModel cabang) {
        cabangDB.save(cabang);
    }

    @Override
    public void updateCabang(CabangModel cabang) {
        cabangDB.save(cabang);
    }

    @Override
    public CabangModel getCabangByIdCabang(Long idCabang){
        return cabangDB.findByIdCabang(idCabang);
    }

    @Override
    public void deleteCabang(CabangModel cabang) { cabangDB.delete(cabang); }

    @Override
    public boolean checkCabang(CabangModel cabang1, CabangModel cabang2) {
        return (cabang1.getIdCabang() == cabang2.getIdCabang()) ? true : false;
    }
}
