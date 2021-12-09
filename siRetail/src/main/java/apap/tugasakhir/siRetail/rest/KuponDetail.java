package apap.tugasakhir.siRetail.rest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class KuponDetail {

    @JsonProperty("idCoupon")
    private String idCoupon;

    @JsonProperty("couponCode")
    private Integer couponCode;

    @JsonProperty("couponName")
    private Integer couponName;

    @JsonProperty("discountAmount")
    private String discountAmount;

    @JsonProperty("expiryDate")
    private String expiryDate;
}
