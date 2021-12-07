package apap.tugasakhir.siRetail.controller;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.model.ItemCabangModel;
import apap.tugasakhir.siRetail.model.UserModel;
import apap.tugasakhir.siRetail.rest.ItemCabangDetail;
import apap.tugasakhir.siRetail.service.CabangService;
import apap.tugasakhir.siRetail.service.ItemCabangRestService;
import apap.tugasakhir.siRetail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class ItemCabangController {
    @Qualifier("itemCabangRestServiceImpl")
    @Autowired
    private ItemCabangRestService itemCabangRestService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @GetMapping("/add-stok/{uuidItem}")
    public String viewAllCabang(
            @PathVariable String uuidItem,
            Model model) {
        ItemCabangModel itemCabangUpdate = itemCabangRestService.getItemCabangByUuid(uuidItem);
        model.addAttribute("itemCabang", itemCabangUpdate);
        return "form-add-stok";
    }

    @PostMapping("/add-stok")
    public String viewAllCabang(
            @ModelAttribute ItemCabangModel itemCabangUpdate,
            Model model,
            final HttpServletRequest httpServletRequest) {

        ItemCabangDetail itemDetail = itemCabangRestService.updateStok(itemCabangUpdate).block();
        String message = "Permintaan untuk menaikkan stok item dengan UUID " + itemDetail.getUuidItem() + " berhasil.";
        model.addAttribute("message", message);
        return "home";
    }
}
