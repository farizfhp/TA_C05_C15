package apap.tugasakhir.siRetail.service;

import apap.tugasakhir.siRetail.model.UserModel;
import apap.tugasakhir.siRetail.repository.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDB userDB;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDB.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public List<UserModel> getListUser() {
        return userDB.findAll();
    }

    @Override
    public UserModel getUserByUsername(String username) {
        return userDB.findByUsername(username);
    }

    @Override
    public UserModel getUserById(Long idUser) {
        Optional<UserModel> user = userDB.findById(idUser);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public void updateUser(UserModel user) {
        userDB.save(user);
    }
}
