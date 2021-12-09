package apap.tugasakhir.siRetail.service;

import java.util.List;

import apap.tugasakhir.siRetail.model.ItemCabangModel;
import apap.tugasakhir.siRetail.rest.ItemCabangDetail;
import apap.tugasakhir.siRetail.rest.ItemDetail;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

public interface ItemCabangRestService {
    ItemCabangDetail updateStok(ItemCabangModel itemCabangUpdate);
    ItemCabangModel getItemCabangById(Long idItemCabang);
    ItemCabangModel getItemCabangByUuid(String uuidItem);
    List<ItemDetail> getAllItem();
}
