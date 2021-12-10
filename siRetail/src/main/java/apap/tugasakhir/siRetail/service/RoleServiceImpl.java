package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.RoleModel;
import apap.tugasakhir.siRetail.repository.RoleDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDB roleDB;

    @Override
    public List<RoleModel> getListRole() {
        return roleDB.findAll();
    }
}
