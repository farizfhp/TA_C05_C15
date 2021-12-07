package apap.tugasakhir.siRetail.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemCabangDetail {
    @JsonProperty("idItem")
    private String uuidItem;

    @JsonProperty("idKategori")
    private Integer idKategori;

    @JsonProperty("tambahanStok")
    private Integer tambahanStok;

    @JsonProperty("idCabang")
    private Long idCabang;

}
