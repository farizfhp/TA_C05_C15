package apap.tugasakhir.siRetail.controller;

import apap.tugasakhir.siRetail.model.UserModel;
import apap.tugasakhir.siRetail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home(@ModelAttribute UserModel user,
            Model model,
            final HttpServletRequest httpServletRequest) {
        if (userService.getUserByUsername(httpServletRequest.getRemoteUser()) == null) {
            return "home";
        }
        String role = userService.getUserByUsername(httpServletRequest.getRemoteUser()).getRole().getNama();
        model.addAttribute("role", role);
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}