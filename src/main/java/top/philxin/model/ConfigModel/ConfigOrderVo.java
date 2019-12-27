package top.philxin.model.ConfigModel;

import lombok.Data;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/26 19:07
 */
@Data
public class ConfigOrderVo {
     String cskaoyan_mall_order_comment;
     String cskaoyan_mall_order_unpaid;
     String cskaoyan_mall_order_unconfirm;

    public ConfigOrderVo() {
    }

    public ConfigOrderVo(String cskaoyan_mall_order_comment, String cskaoyan_mall_order_unpaid, String cskaoyan_mall_order_unconfirm) {
        this.cskaoyan_mall_order_comment = cskaoyan_mall_order_comment;
        this.cskaoyan_mall_order_unpaid = cskaoyan_mall_order_unpaid;
        this.cskaoyan_mall_order_unconfirm = cskaoyan_mall_order_unconfirm;
    }
}
