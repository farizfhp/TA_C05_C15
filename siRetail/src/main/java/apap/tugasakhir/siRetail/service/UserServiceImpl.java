package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.UserModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Override
    public List<UserModel> getListUser() {
        return null;
    }
}
