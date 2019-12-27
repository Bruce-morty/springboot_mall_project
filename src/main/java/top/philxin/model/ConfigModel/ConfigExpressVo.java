package top.philxin.model.ConfigModel;

import lombok.Data;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/26 19:07
 */
@Data
public class ConfigExpressVo {
    String cskaoyan_mall_express_freight_min;
    String cskaoyan_mall_express_freight_value;

    public ConfigExpressVo(String cskaoyan_mall_express_freight_min, String cskaoyan_mall_express_freight_value) {
        this.cskaoyan_mall_express_freight_min = cskaoyan_mall_express_freight_min;
        this.cskaoyan_mall_express_freight_value = cskaoyan_mall_express_freight_value;
    }

    public ConfigExpressVo() {
    }
}
