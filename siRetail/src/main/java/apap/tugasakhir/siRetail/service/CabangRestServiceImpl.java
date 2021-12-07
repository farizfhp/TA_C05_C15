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
}
