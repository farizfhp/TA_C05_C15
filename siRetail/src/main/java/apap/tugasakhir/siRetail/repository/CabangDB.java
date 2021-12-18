package apap.tugasakhir.siRetail.repository;

import apap.tugasakhir.siRetail.model.CabangModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CabangDB extends JpaRepository<CabangModel, Long> {
    <Optional> CabangModel findByIdCabang(Long idCabang);
}