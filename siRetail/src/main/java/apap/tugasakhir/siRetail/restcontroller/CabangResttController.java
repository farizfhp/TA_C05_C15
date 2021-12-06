package apap.tugasakhir.siRetail.restcontroller;

import apap.tugasakhir.siRetail.service.CabangRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CabangResttController {

    @Autowired
    private CabangRestService cabangRestService;

    @GetMapping(value="/list-alamat-cabang")
    private List<String> retrieveListAlamatCabang(){
        return cabangRestService.retrieveListAlamatCabang();
    }

}
