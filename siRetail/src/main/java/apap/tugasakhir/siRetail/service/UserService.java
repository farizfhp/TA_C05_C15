package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    List<UserModel> getListUser();
    UserModel getUserByUsername(String username);

}
