package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.CabangModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemCabangServiceImpl implements ItemCabangService{

    @Override
    public List<CabangModel> getListItem() {
        return null;
    }
}
