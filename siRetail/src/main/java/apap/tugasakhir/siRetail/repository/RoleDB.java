package apap.tugasakhir.siRetail.repository;

import apap.tugasakhir.siRetail.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDB extends JpaRepository<RoleModel, Long> {
    RoleModel findByIdRole(Long idRole);
}