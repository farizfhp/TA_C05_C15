package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.ItemCabangModel;
import apap.tugasakhir.siRetail.rest.ItemCabangDetail;
import reactor.core.publisher.Mono;

public interface ItemCabangRestService {
    Mono<ItemCabangDetail> updateStok(ItemCabangModel itemCabangUpdate);
    ItemCabangModel getItemCabangById(Long idItemCabang);
    ItemCabangModel getItemCabangByUuid(String uuidItem);
}
