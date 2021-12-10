package apap.tugasakhir.siRetail.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KuponDetail {

    @JsonProperty("id-coupon")
    private Integer idCoupon;

    @JsonProperty("coupon-code")
    private String couponCode;

    @JsonProperty("coupon-name")
    private String couponName;

    @JsonProperty("discount-amount")
    private Float discountAmount;

    @JsonProperty("expiry-date")
    private String expiryDate;
}
