package apap.tugasakhir.siRetail.rest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class KuponDetail {

    @JsonProperty("id-coupon")
    private String idCoupon;

    @JsonProperty("coupon-code")
    private String couponCode;

    @JsonProperty("coupon-name")
    private String couponName;

    @JsonProperty("discount-amount")
    private String discountAmount;

    @JsonProperty("expiry-date")
    private String expiryDate;
}
