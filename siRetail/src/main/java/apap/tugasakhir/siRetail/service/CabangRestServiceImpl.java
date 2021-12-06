package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.repository.CabangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CabangRestServiceImpl implements CabangRestService{

    @Autowired
    private CabangDB cabangDB;

    @Override
    public List<String> retrieveListAlamatCabang(){
        List<CabangModel> listCabang = cabangDB.findAll();
        List<String> listAlamatCabang = new ArrayList<>();

        for (CabangModel cabang : listCabang){
            String alamat = cabang.getAlamat();
            listAlamatCabang.add(alamat);
        }
        return listAlamatCabang;
    }



    public CabangModel createCabang(CabangModel cabang) {
        cabang.setStatus(0);
        return cabangDB.save(cabang);
    }
}
