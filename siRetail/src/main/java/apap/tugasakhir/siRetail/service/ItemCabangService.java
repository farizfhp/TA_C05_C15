package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.ItemCabangModel;

import java.util.List;
import java.util.Optional;

public interface ItemCabangService {
    List<ItemCabangModel> getListItem();

    void addItemCabang(ItemCabangModel item);

    boolean deleteItemCabang(ItemCabangModel item);

    Optional<ItemCabangModel> getItemCabangByIdItemCabang(Long idItemCabang);

    ItemCabangModel getItemById(Long idItemCabang);
}
