package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    List<UserModel> getListUser();
    UserModel getUserByUsername(String username);
    UserModel getUserById(Long idUser);
    void updateUser(UserModel user);

}
