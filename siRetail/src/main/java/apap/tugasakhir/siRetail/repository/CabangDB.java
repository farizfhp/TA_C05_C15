package apap.tugasakhir.siRetail.repository;

import apap.tugasakhir.siRetail.model.CabangModel;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
public interface CabangDB extends JpaRepository<CabangModel,Long> {
    <Optional>CabangModel findByIdCabang(Long idCabang);
=======
public interface CabangDB extends JpaRepository<CabangModel, Long> {
    <Optional> CabangModel findByIdCabang(Long idCabang);
>>>>>>> accbcd8371bb0e66ca9a6820cb41f981176624b7
}