package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.ItemCabangModel;
import apap.tugasakhir.siRetail.repository.ItemCabangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemCabangServiceImpl implements ItemCabangService {

    @Autowired
    ItemCabangDB itemCabangDB;

    @Override
    public List<ItemCabangModel> getListItem() {
        return itemCabangDB.findAll();
    }

    @Override
    public void addItemCabang(ItemCabangModel item) {
        List<ItemCabangModel> listItem = getListItem();

        for (ItemCabangModel itemIter : listItem) {
            if (itemIter.getUuidItem().equals(item.getUuidItem())) {
                System.out.println("masuk atas");
                if (itemIter.getCabang() == item.getCabang()) {
                    // ItemCabangModel itemTemp = itemIter;
                    System.out.println("masuk serppis");
                    System.out.println(itemIter.getStok());
                    System.out.println(item.getStok());
                    System.out.println(itemIter.getStok() + item.getStok());
                    itemIter.setStok(itemIter.getStok() + item.getStok());
                    itemCabangDB.save(itemIter);
                    System.out.println("masuk tengah");
                    return;
                }
            }
        }
        System.out.println(item.getStok());
        itemCabangDB.save(item);
    }

    @Override
    public boolean deleteItemCabang(ItemCabangModel item) {
        itemCabangDB.delete(item);
        return true;
    }

    @Override
    public Optional<ItemCabangModel> getItemCabangByIdItemCabang(Long idItemCabang){
        return itemCabangDB.findByIdItemCabang(idItemCabang);
    }

    @Override
    public ItemCabangModel getItemById(Long idItemCabang){
        Optional<ItemCabangModel> item = itemCabangDB.findByIdItemCabang(idItemCabang);
        if (item.isPresent()) {
            return item.get();
        }
        return null;
    }

}
