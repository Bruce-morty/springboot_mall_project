package top.philxin.model.WxCartModel;

import lombok.Data;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/31 15:21
 */
@Data
public class CheckProductVo {
    private Boolean isChecked;
    private int[] productIds;
}
