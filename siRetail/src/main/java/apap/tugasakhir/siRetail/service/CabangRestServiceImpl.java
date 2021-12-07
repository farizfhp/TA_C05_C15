package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.repository.CabangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CabangRestServiceImpl implements CabangRestService{

    @Autowired
    private CabangDB cabangDB;

    @Override
    public HashMap<String, String> retrieveListAlamatCabang(){
        List<CabangModel> listCabang = cabangDB.findAll();
        HashMap<String,String> mapAlamatCabang = new HashMap<>();

        for (CabangModel cabang : listCabang){
            String idCabang = String.valueOf(cabang.getIdCabang());
            String alamat = cabang.getAlamat();
            mapAlamatCabang.put(idCabang,alamat);
        }
        return mapAlamatCabang;
    }



    public CabangModel createCabang(CabangModel cabang) {
        cabang.setStatus(0);
        return cabangDB.save(cabang);
    }
}
