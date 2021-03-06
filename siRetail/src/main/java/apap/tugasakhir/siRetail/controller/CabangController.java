package apap.tugasakhir.siRetail.controller;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.model.UserModel;
import apap.tugasakhir.siRetail.service.CabangService;
import apap.tugasakhir.siRetail.service.ItemCabangService;
import apap.tugasakhir.siRetail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
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

    @Qualifier("itemCabangServiceImpl")
    @Autowired
    private ItemCabangService itemCabangService;

    @GetMapping("/cabang")
    public String viewAllCabang(Model model,
            final HttpServletRequest httpServletRequest) {
        String role = userService.getUserByUsername(httpServletRequest.getRemoteUser()).getRole().getNama();
        System.out.println(role);
        List<CabangModel> listCabangManager = new ArrayList<>();
        List<CabangModel> listCabang = cabangService.getListCabang();
        if (role.equals("Manager Cabang")) {
            System.out.println("MASUKK");
            for (CabangModel cabang : listCabang) {
                if (cabang.getUser() != null) {
                    if (cabang.getUser().getUsername()
                            .equals(userService.getUserByUsername(httpServletRequest.getRemoteUser()).getUsername())) {
                        System.out.println("MASUK JUGA");
                        listCabangManager.add(cabang);
                    }
                }

            }
            model.addAttribute("listCabang", listCabangManager);
            return "viewall-cabang";
        }

        model.addAttribute("listCabang", listCabang);
        return "viewall-cabang";
    }

    @GetMapping("/cabang/view")
    public String viewDetailCabang(
            @RequestParam(value = "idCabang", required = false) Long idCabang, final HttpServletRequest httpreq,
            Model model) {
        CabangModel cabang = cabangService.getCabangByIdCabang(idCabang);
        model.addAttribute("cabang", cabang);

        // nambahin buat list all item di tiap cabang
        // model.addAttribute("listItemCabang", cabang.getListItemCabang());
        return "view-cabang";
    }

    @GetMapping("/cabang/view/onhold")
    public String viewAllCabangOnHold(Model model) {
        List<CabangModel> listCabang = cabangService.getListCabangByStatus(0);
        model.addAttribute("listCabang", listCabang);
        return "accdec-cabang";
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
            Model model,
            final HttpServletRequest httpServletRequest) {
        cabangService.addCabang(cabang);
        String message = "Cabang dengan nama " + cabang.getNama() + " berhasil ditambahkan.";
        // String message = "Cabang dengan id " + String.valueOf(cabang.getIdCabang()) +
        // " berhasil ditambahkan.";

        return returnMessage(model, httpServletRequest, message);
    }

    @GetMapping("/cabang/update/{idCabang}")
    public String updateCabangForm(
            @PathVariable(required = false) Long idCabang, Model model) {
        CabangModel cabang = cabangService.getCabangByIdCabang(idCabang);

        model.addAttribute("cabang", cabang);
        return "form-update-cabang";
    }

    @PostMapping("/cabang/update")
    public String updateCabangSubmit(
            @ModelAttribute CabangModel cabang,
            Model model,
            final HttpServletRequest httpServletRequest) {
        cabangService.updateCabang(cabang);
        System.out.println(cabang.getUkuran());
        String message = "Cabang dengan nama " + cabang.getNama() + " berhasil diubah.";
        // String message = "Cabang dengan id " + String.valueOf(cabang.getIdCabang()) +
        // " berhasil ditambahkan.";
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

    @GetMapping("/cabang/delete/{idCabang}")
    public String removeCabang(
            @PathVariable(required = false) Long idCabang,
            Model model,
            final HttpServletRequest httpServletRequest) {
        CabangModel cabang = cabangService.getCabangByIdCabang(idCabang);
        model.addAttribute("cabang", cabang);
        if (cabang.getListItemCabang().size() == 0) {
            cabangService.deleteCabang(cabang);
            String message = "Cabang dengan nama " + cabang.getNama() + " berhasil dihapus.";
            return returnMessage(model, httpServletRequest, message);
        } else {
            return returnMessage(model, httpServletRequest, "Cabang tidak dapat dihapus karena masih memiliki item.");
        }

    }

    @GetMapping("/cabang/request/{status}/{idCabang}")
    public String requestCabang(
            @PathVariable(required = false) String status,
            @PathVariable(required = false) Long idCabang,
            Model model,
            final HttpServletRequest httpServletRequest) {
        CabangModel cabang = cabangService.getCabangByIdCabang(idCabang);

        String namaCabang = cabang.getNama();

        if (status.equals("accept"))
            cabang.setStatus(2);
        if (status.equals("decline"))
            cabangService.deleteCabang(cabang);
        ;

        cabang.setUser(userService.getUserByUsername(httpServletRequest.getRemoteUser()));

        String message = "Cabang dengan nama " + namaCabang + " berhasil di-" + status;
        return returnMessage(model, httpServletRequest, message);
    }

}
