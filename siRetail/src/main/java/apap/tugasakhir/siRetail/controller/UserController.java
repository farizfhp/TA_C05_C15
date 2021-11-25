package apap.tugasakhir.siRetail.controller;

import apap.tugasakhir.siRetail.model.RoleModel;
import apap.tugasakhir.siRetail.model.UserModel;
import apap.tugasakhir.siRetail.service.RoleService;
import apap.tugasakhir.siRetail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
            Model model
    ){
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/";
    }
}
