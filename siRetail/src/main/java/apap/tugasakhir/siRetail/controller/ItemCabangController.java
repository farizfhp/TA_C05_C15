package apap.tugasakhir.siRetail.controller;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.model.ItemCabangModel;
import apap.tugasakhir.siRetail.model.UserModel;
import apap.tugasakhir.siRetail.rest.ItemCabangDetail;
import apap.tugasakhir.siRetail.rest.ItemDetail;
import apap.tugasakhir.siRetail.rest.KuponDetail;
import apap.tugasakhir.siRetail.service.*;
import org.springframework.validation.BindingResult;
import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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

    @Qualifier("cabangRestServiceImpl")
    @Autowired
    private CabangRestService cabangRestService;

    @GetMapping("/itemCabang/add/{idCabang}")
    public String addItemCabangForm(
            @PathVariable Long idCabang,
            Model model) {

        ItemCabangModel item = new ItemCabangModel();
        CabangModel cabang = cabangService.getCabangByIdCabang(idCabang);
        // cabang.getListItemCabang().add(item);
        // List<ItemDetail> listItemAll = itemCabangRestService.getAllItem();
        List<ItemDetail> listItemExist = itemCabangRestService.getAllItem();
//        List<ItemCabangModel> listItemNew = new ArrayList<>();

        // System.out.println(Arrays.deepToString(itemCabangRestService.getAllItem().toArray()));
        item.setCabang(cabang);
        model.addAttribute("itemNew", item);
        model.addAttribute("cabang", cabang);
        // model.addAttribute("itemcabang", item);
        model.addAttribute("listItemExist", listItemExist);
//         model.addAttribute("listItemNew", listItemNew);
        return "form-add-itemcabang";
    }

    @PostMapping(value = "/itemCabang/add", params = { "save" })
    public String addItemCabangSubmit(String uuid, Integer id, Model model,
            @ModelAttribute ItemCabangModel item,
            final HttpServletRequest httpServletRequest) {

        // for ( ItemCabangModel itemCabang : itemCabangService.getListItem() ){
        // if (itemCabang.getIdItemCabang().equals())
        // }
        // item.getCabang().setListItemCabang(listItemNew);
        
        List<ItemDetail> listItem = itemCabangRestService.getAllItem();

        ItemCabangModel itemTemp = new ItemCabangModel();
        for (ItemDetail itemIter:listItem){
            if (itemIter.getUuid().equals(item.getUuidItem())){
                itemTemp.setUuidItem(item.getUuidItem());
                itemTemp.setCabang(item.getCabang());
                itemTemp.setNama(itemIter.getNama());
                itemTemp.setHarga(itemIter.getHarga());
                itemTemp.setStok(item.getStok());
                itemTemp.setKategori(itemIter.getKategori());
            }
        }
        itemCabangService.addItemCabang(itemTemp);

        // item.getCabang().getListItemCabang().add(itemTemp);

        // ItemCabangModel itemEx = itemCabangRestService.getItemCabangByUuid(uuid);
        // item.getCabang().getListItemCabang().add(itemEx);
        // itemCabangService.addItemCabang(item);
        // model.addAttribute("itemEx", item.getNama());
        String message = "Item dengan nama " + itemTemp.getNama() + " berhasil ditambahkan.";
        model.addAttribute("message", message);
        model.addAttribute("listItemCabang", item.getCabang().getListItemCabang());
        model.addAttribute("cabang", item.getCabang());
        return "view-cabang";
    }

    @PostMapping(value = "/itemCabang/add", params = {"addRow"})
    private String addRowItemMultiple(@ModelAttribute CabangModel cabang, Model model) {
        if (cabang.getListItemCabang() == null || cabang.getListItemCabang().size() == 0){
            cabang.setListItemCabang(new ArrayList<>());
            System.out.println("bikin baru");
        }
        cabang.getListItemCabang().add(new ItemCabangModel());
        System.out.println("nambah baru");
        List<ItemCabangModel> listItemCabang = cabang.getListItemCabang();

        model.addAttribute("cabang", cabang);
        model.addAttribute("listItemCabangExist", listItemCabang);
        return "form-add-itemcabang";
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

    @GetMapping(value = "/itemCabang/promo/{idItemCabang}")
    private String listCoupon( @PathVariable Long idItemCabang, Model model){
        List<KuponDetail> listCoupon = cabangRestService.listCoupon();
        ItemCabangModel itemCabangModel = itemCabangRestService.getItemCabangById(idItemCabang);
        model.addAttribute("item",itemCabangModel);
        model.addAttribute("idItemCabang",idItemCabang);
        model.addAttribute ( "listKupon",listCoupon);
        model.addAttribute("classActiveSettings","active");
        return "viewall-kupon" ;
    }

    @GetMapping("/itemCabang/promo/{idItemCabang}/applyCoupon/{idCoupon}")
    // cabang/id item/apply-coupon/idCoupon
    public String applyCoupon(
            @PathVariable(required = true) Long idItemCabang,
            @PathVariable(required = true) Integer idCoupon,
            Model model) {

        List<KuponDetail> listCoupon = cabangRestService.listCoupon();
        for (KuponDetail kupon: listCoupon){
            if (kupon.getIdCoupon().equals(idCoupon)){
                itemCabangRestService.applyCoupon(idItemCabang,idCoupon,kupon.getDiscountAmount());
                break;
            }
        }

        return "redirect:/cabang";
    }

}
