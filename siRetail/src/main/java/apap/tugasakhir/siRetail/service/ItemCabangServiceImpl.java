package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.ItemCabangModel;
import apap.tugasakhir.siRetail.repository.ItemCabangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemCabangServiceImpl implements ItemCabangService{

    @Autowired
    ItemCabangDB itemCabangDB;

    @Override
    public List<ItemCabangModel> getListItem() {
        return itemCabangDB.findAll();
    }

    @Override
    public void addItemCabang(ItemCabangModel item) {
        itemCabangDB.save(item);
    }

    @Override
    public boolean deleteItemCabang(ItemCabangModel item) {
        itemCabangDB.delete(item);
        return true;
    }

}
