package apap.tugasakhir.siRetail.restcontroller;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.rest.KuponDetail;
import apap.tugasakhir.siRetail.rest.ResponseReader;
import apap.tugasakhir.siRetail.service.CabangRestService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CabangResttController {
    @Autowired
    CabangRestService cabangRestService;

    @PostMapping(value = "/cabang")
    private CabangModel createCabang(@Valid @RequestBody CabangModel cabang, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            return cabangRestService.createCabang(cabang);
        }
    }

    @GetMapping(value="/list-alamat-cabang")
    private ArrayList<HashMap<String,String>> retrieveListAlamatCabang(){
//        ResponseReader response = new ResponseReader();
//        response.setStatus(200);
//        response.setMessage("success");
//        response.setResult(cabangRestService.retrieveListAlamatCabang());
//        return response;
        return cabangRestService.retrieveListAlamatCabang();
    }

    @GetMapping(value = "/list-cabang")
    private List<CabangModel> retrieveListCabang() {
        return cabangRestService.retrieveListCabang();
    }

    @GetMapping(value = "/list-coupon")
    private  List<KuponDetail> listCoupon(Model model){
        return cabangRestService.listCoupon();
        //var objResponse1 = JsonConvert.DeserializeObject<List<RetrieveMultipleResponse>>(JsonStr);
    }
}
