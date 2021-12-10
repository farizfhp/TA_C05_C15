package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.ItemCabangModel;

import java.util.List;

public interface ItemCabangService {
    List<ItemCabangModel> getListItem();
    void addItemCabang(ItemCabangModel item);
}
