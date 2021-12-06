package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.repository.CabangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CabangRestServiceImpl implements CabangRestService {
    @Autowired
    private CabangDB cabangDB;

    @Override
    public CabangModel createCabang(CabangModel cabang) {
        cabang.setStatus(0);
        return cabangDB.save(cabang);
    }
}
