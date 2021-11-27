package apap.tugasakhir.siRetail.controller;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.service.CabangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans. factory.annotation.Qualifier;
import org. springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CabangController {
    @Qualifier("cabangServiceImpl")
    @Autowired
    private CabangService cabangService;

    @GetMapping("/cabang")
    public String viewAllCabang(Model model) {
        List<CabangModel> listCabang = cabangService.getListCabang();
        model.addAttribute("listCabang", listCabang);
        return "viewall-cabang";
    }
}
