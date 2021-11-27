package apap.tugasakhir.siRetail.controller;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.model.UserModel;
import apap.tugasakhir.siRetail.service.CabangService;
import apap.tugasakhir.siRetail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans. factory.annotation.Qualifier;
import org. springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class CabangController {
    @Qualifier("cabangServiceImpl")
    @Autowired
    private CabangService cabangService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @GetMapping("/cabang")
    public String viewAllCabang(Model model) {
        List<CabangModel> listCabang = cabangService.getListCabang();
        model.addAttribute("listCabang", listCabang);
        return "viewall-cabang";
    }

    @GetMapping("/cabang/add")
    public String addCabangForm(Model model, final HttpServletRequest httpServletRequest) {
        CabangModel cabang = new CabangModel();
        UserModel user = userService.getUserByUsername(httpServletRequest.getRemoteUser());
        cabang.setUser(user);

        model.addAttribute("cabang", cabang);
        return "form-add-cabang";
    }

    @PostMapping("/cabang/add")
    public String addCabangSubmit(
            @ModelAttribute CabangModel cabang,
            Model model
    ) {
        cabangService.addCabang(cabang);
        String message = "Cabang dengan nama " + cabang.getNama() + " berhasil ditambahkan.";
//        String message = "Cabang dengan id " + String.valueOf(cabang.getIdCabang()) + " berhasil ditambahkan.";
        model.addAttribute("message", message);
        return "home";
    }
}
