package apap.tugasakhir.siRetail.repository;

import apap.tugasakhir.siRetail.model.ItemCabangModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCabangDB extends JpaRepository<ItemCabangModel,Long> {
    ItemCabangModel findByIdItemCabang(Long idItemCabang);
}