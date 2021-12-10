package apap.tugasakhir.siRetail.service;

import java.util.List;

import apap.tugasakhir.siRetail.model.ItemCabangModel;
import apap.tugasakhir.siRetail.rest.ItemCabangDetail;
import apap.tugasakhir.siRetail.rest.ItemDetail;
import apap.tugasakhir.siRetail.rest.ResponseReader;

public interface ItemCabangRestService {
    ItemCabangDetail updateStok(ItemCabangModel itemCabangUpdate);

    ItemCabangModel getItemCabangById(Long idItemCabang);

    ItemCabangModel getItemCabangByUuid(String uuidItem);

    List<ItemDetail> getAllItem();

    void applyCoupon(Long idItemCabang, Integer idCoupon, Float discountAmount);

    ResponseReader updateSiItem(ItemCabangModel item, Integer stokUpdate);

    Integer getItemStok(String uuid);

}
