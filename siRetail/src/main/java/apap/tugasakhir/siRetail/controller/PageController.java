package apap.tugasakhir.siRetail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {
    @Autowired
//    private UserService userService;

    @RequestMapping("/")
    public String home (){
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}