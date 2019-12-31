package top.philxin.model.WxCartModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.philxin.model.Address;
import top.philxin.model.Cart;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/31 20:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutVo {
    BigDecimal grouponPrice;

    int grouponRulesId;

    Address checkedAddress;

    BigDecimal actualPrice;

    BigDecimal orderTotalPrice;

    BigDecimal couponPrice;

    int availableCouponLength;

    int couponId;

    BigDecimal freightPrice;

    List<Cart> checkedGoodsList;

    BigDecimal goodsTotalPrice;

    int addressId;

}
