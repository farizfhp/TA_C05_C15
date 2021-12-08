package apap.tugasakhir.siRetail.controller;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.model.ItemCabangModel;
import apap.tugasakhir.siRetail.model.UserModel;
import apap.tugasakhir.siRetail.rest.ItemCabangDetail;
import apap.tugasakhir.siRetail.rest.ItemDetail;
import apap.tugasakhir.siRetail.service.CabangService;
import apap.tugasakhir.siRetail.service.ItemCabangRestService;
import apap.tugasakhir.siRetail.service.ItemCabangService;
import apap.tugasakhir.siRetail.service.UserService;
import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Qualifier("itemCabangRestServiceImpl")
    @Autowired
    private ItemCabangRestService itemCabangRestService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @GetMapping("/itemCabang/add/{idCabang}")
    public String addItemCabangForm(
            @PathVariable Long idCabang,
            Model model) {

        ItemCabangModel item = new ItemCabangModel();
        CabangModel cabang = cabangService.getCabangByIdCabang(idCabang);
        // cabang.getListItemCabang().add(item);
        // List<ItemDetail> listItemAll = itemCabangRestService.getAllItem();
        List<ItemDetail> listItem = itemCabangRestService.getAllItem();

//        List<ItemDetail> listItemNew = new ArrayList<>();
//        listItemNew.add(new ItemDetail(item.getUuidItem(), item.getNama(), item.getHarga(), item.getStok(), item.getKategori()));
//        item.setCabang(cabang);
        System.out.println(Arrays.deepToString(itemCabangRestService.getAllItem().toArray()));

        model.addAttribute("cabang", cabang);
        // model.addAttribute("itemcabang", item);
        model.addAttribute("listItem", listItem);
//        model.addAttribute("listItemNew", listItemNew);
        return "form-add-itemcabang";
    }

    @PostMapping(value = "/itemCabang/add", params = { "save" })
    public String addItemCabangSubmit(String uuid, Integer id, Model model,
            @ModelAttribute ItemCabangModel item,
            final HttpServletRequest httpServletRequest) {

//        for ( ItemCabangModel itemCabang : itemCabangService.getListItem() ){
//            if (itemCabang.getIdItemCabang().equals())
//        }
//        item.getCabang().setListItemCabang(listItemNew);

        ItemCabangModel itemEx = itemCabangRestService.getItemCabangByUuid(uuid);
        item.getCabang().getListItemCabang().add(itemEx);
        itemCabangService.addItemCabang(item);
        model.addAttribute("itemEx", item.getNama());
        String message = "Item dengan nama " + item.getNama() + " berhasil ditambahkan.";

        return returnMessage(model, httpServletRequest, message);
    }

    private String returnMessage(Model model, HttpServletRequest httpServletRequest, String message) {
        model.addAttribute("message", message);

        if (userService.getUserByUsername(httpServletRequest.getRemoteUser()) == null) {
            return "home";
        }
        String role = userService.getUserByUsername(httpServletRequest.getRemoteUser()).getRole().getNama();
        model.addAttribute("role", role);
        model.addAttribute("message", message);
        return "home";
    }

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
