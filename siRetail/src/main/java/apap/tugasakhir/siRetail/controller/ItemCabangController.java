package apap.tugasakhir.siRetail.controller;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.model.ItemCabangModel;
import apap.tugasakhir.siRetail.service.CabangService;
import apap.tugasakhir.siRetail.service.ItemCabangService;
import apap.tugasakhir.siRetail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class ItemCabangController {
    @Qualifier("itemCabangServiceImpl")
    @Autowired
    private ItemCabangService itemCabangService;

    @Qualifier("cabangServiceImpl")
    @Autowired
    private CabangService cabangService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @GetMapping("/itemCabang/add/{idCabang}")
    public String addItemCabangForm(
            @PathVariable Long idCabang,
            Model model)
    {
        ItemCabangModel item = new ItemCabangModel();
        CabangModel cabang = cabangService.getCabangByIdCabang(idCabang);
        item.setCabang(cabang);
        model.addAttribute("itemcabang", item);
        return "form-add-itemcabang";
    }

    @PostMapping("/itemCabang/add")
    public String addItemCabangSubmit(
            @ModelAttribute ItemCabangModel item,
            Model model,
            final HttpServletRequest httpServletRequest)
    {
        itemCabangService.addItemCabang(item);
//        model.addAttribute("idCabang", item.getCabang().getIdCabang());
//        model.addAttribute("nama", item.getNama());
        String message = "Item dengan nama " + item.getNama() + " berhasil ditambahkan.";

        return returnMessage(model, httpServletRequest, message);
    }

    private String returnMessage(Model model, HttpServletRequest httpServletRequest, String message) {
        model.addAttribute("message", message);

        if(userService.getUserByUsername(httpServletRequest.getRemoteUser())==null){
            return "home";
        }
        String role = userService.getUserByUsername(httpServletRequest.getRemoteUser()).getRole().getNama();
        model.addAttribute("role",role);

        return "home";
    }
}
