package top.philxin.model.WxCartModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/30 21:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddGoodsVo {
    Integer goodsId;
    Short number;
    Integer productId;
}
