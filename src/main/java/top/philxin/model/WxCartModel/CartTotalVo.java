package top.philxin.model.WxCartModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/30 23:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartTotalVo {
    Short goodsCount;
    Short checkedGoodsCount;
    //这是购物车所有商品的总价
    BigDecimal goodsAmount;
    //这是购物车中选中商品的总价
    BigDecimal checkedGoodsAmount;
}
