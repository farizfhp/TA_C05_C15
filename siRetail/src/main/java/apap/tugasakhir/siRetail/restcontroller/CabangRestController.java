package apap.tugasakhir.siRetail.restcontroller;

import apap.tugasakhir.siRetail.model.CabangModel;
import apap.tugasakhir.siRetail.service.CabangRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CabangRestController {
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
    @GetMapping(value = "/list-cabang")
    private List<CabangModel> retrieveListCabang() {
        return cabangRestService.retrieveListCabang();
    }
}
