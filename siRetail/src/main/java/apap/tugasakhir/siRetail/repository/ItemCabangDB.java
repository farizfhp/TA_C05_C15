package apap.tugasakhir.siRetail.repository;

import apap.tugasakhir.siRetail.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCabangDB extends JpaRepository<UserModel,Long> {
    UserModel findByIdItemCabang(String idItemCabang);
}