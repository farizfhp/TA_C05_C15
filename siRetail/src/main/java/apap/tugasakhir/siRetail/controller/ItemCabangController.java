package apap.tugasakhir.siRetail.controller;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.model.ItemCabangModel;
import apap.tugasakhir.siRetail.rest.ItemCabangDetail;
import apap.tugasakhir.siRetail.rest.ItemDetail;
import apap.tugasakhir.siRetail.rest.KuponDetail;
import apap.tugasakhir.siRetail.service.CabangRestService;
import apap.tugasakhir.siRetail.service.CabangService;
import apap.tugasakhir.siRetail.service.ItemCabangRestService;
import apap.tugasakhir.siRetail.service.ItemCabangService;
import apap.tugasakhir.siRetail.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
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
        List<ItemDetail> listItem = itemCabangRestService.getAllItem();

        item.setCabang(cabang);
        model.addAttribute("itemNew", item);
        model.addAttribute("cabang", cabang);
        model.addAttribute("listItem", listItem);
        return "form-add-itemcabang";
    }

    @PostMapping(value = "/itemCabang/add", params = { "save" })
    public String addItemCabangSubmit(String uuid, Integer id, Model model,
            @ModelAttribute ItemCabangModel item,
            final HttpServletRequest httpServletRequest) {
        
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

        String message = "Item dengan nama " + itemTemp.getNama() + " berhasil ditambahkan.";
        model.addAttribute("message", message);
        model.addAttribute("listItemCabang", item.getCabang().getListItemCabang());
        model.addAttribute("cabang", item.getCabang());
        return "view-cabang";
    }

    @GetMapping("/itemCabang/tambahStok/{idCabang}/{uuidCabang}")
    public String addStokItemCabangForm(
            @PathVariable String uuidCabang,
            @PathVariable Long idCabang,
            Model model) {

        ItemCabangModel item = new ItemCabangModel();
        List<ItemDetail> listItem = itemCabangRestService.getAllItem();
        CabangModel cabang = cabangService.getCabangByIdCabang(idCabang);
        
        item.setCabang(cabang);
        model.addAttribute("cabang", cabang);
        model.addAttribute("itemNew", item);
        model.addAttribute("listItem", listItem);
        return "form-add-stok-request";
    }

    @PostMapping(value = "/itemCabang/tambahStok", params = { "save" })
    public String addStokItemCabangSubmit(Model model,
            @ModelAttribute ItemCabangModel item,
            final HttpServletRequest httpServletRequest) {
        
        List<ItemDetail> listItem = itemCabangRestService.getAllItem();
        System.out.println("ITEM CABANGG == " + item.getCabang().getIdCabang());

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
        // itemCabangService.addItemCabang(itemTemp);
        ItemCabangDetail response = itemCabangRestService.updateStok(itemTemp);
        // System.out.println("UPDATE STOK ITEM ===== " + response.getTambahanStok());
        // System.out.println("UPDATE STOK ITEM ===== " + response);

        String message = "Item dengan ID " + response.getUuidItem() + " berhasil ditambahkan.";
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
    @PostMapping("/itemCabang/delete")
    public String deletePenjaga(
            @ModelAttribute CabangModel cabang,
            Model model
    ) {
        model.addAttribute("idCabang", cabang.getIdCabang());
        boolean res = false;
        for (ItemCabangModel item : cabang.getListItemCabang()) {
            res = itemCabangService.deleteItemCabang(item);
        }
        if (res == true) {
            String message = "Item yang dipilih berhasil dihapus.";
            model.addAttribute("message", message);
            return "view-cabang";
        }
        return "";
    }


}
