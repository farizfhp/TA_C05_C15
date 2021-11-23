package apap.tugasakhir.siRetail.repository;

import apap.tugasakhir.siRetail.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabangDB extends JpaRepository<UserModel,Long> {
    UserModel findByIdCabang(String idCabang);
}