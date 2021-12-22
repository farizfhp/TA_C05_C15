package apap.tugasakhir.siRetail.controller;

import apap.tugasakhir.siRetail.model.RoleModel;
import apap.tugasakhir.siRetail.model.UserModel;
import apap.tugasakhir.siRetail.service.RoleService;
import apap.tugasakhir.siRetail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/add")
    private String addUserForm(Model model) {
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.getListRole();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        return "form-add-user";
    }

    @PostMapping(value = "/add")
    private String addUserSubmit(
            @ModelAttribute UserModel user,
            Model model,
            final HttpServletRequest httpServletRequest) {
        userService.addUser(user);
        model.addAttribute("user", user);
        String message = "User dengan username " + user.getUsername() + " berhasil ditambahkan.";
        return returnMessage(model, httpServletRequest, message);
    }

    @GetMapping("/viewall")
    public String listUser(Model model, final HttpServletRequest httpServletRequest) {
        List<UserModel> listUser = userService.getListUser();
        String role = userService.getUserByUsername(httpServletRequest.getRemoteUser()).getRole().getNama();
        model.addAttribute("role", role);
        model.addAttribute("listUser", listUser);
        model.addAttribute("classActiveSettings", "active");
        return "viewall-user";
    }

    @GetMapping("/update/{idUser}")
    public String updateUserForm(
            @PathVariable Long idUser,
            Model model,
            final HttpServletRequest httpServletRequest) {
        UserModel user = userService.getUserById(idUser);
        List<RoleModel> listRole = roleService.getListRole();
        String role = userService.getUserByUsername(httpServletRequest.getRemoteUser()).getRole().getNama();
        model.addAttribute("role", role);
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        return "form-update-user";
    }

    @PostMapping("/update")
    public String updateUserSubmit(
            @ModelAttribute UserModel user,
            Model model,
            final HttpServletRequest httpServletRequest) {
        userService.updateUser(user);
        model.addAttribute("user", user.getUsername());
        String message = "User dengan username " + user.getUsername() + " berhasil diupdate.";
        return returnMessage(model, httpServletRequest, message);
    }

    private String returnMessage(Model model, HttpServletRequest httpServletRequest, String message) {
        model.addAttribute("message", message);

        if (userService.getUserByUsername(httpServletRequest.getRemoteUser()) == null) {
            return "home";
        }
        String role = userService.getUserByUsername(httpServletRequest.getRemoteUser()).getRole().getNama();
        model.addAttribute("role", role);

        return "home";
    }

}
