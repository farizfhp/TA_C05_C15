package apap.tugasakhir.siRetail.repository;

import apap.tugasakhir.siRetail.model.ItemCabangModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCabangDB extends JpaRepository<ItemCabangModel, Long> {
    Optional<ItemCabangModel> findByIdItemCabang(Long idItemCabang);

    Optional<ItemCabangModel> findByUuidItem(String uuidItem);
}