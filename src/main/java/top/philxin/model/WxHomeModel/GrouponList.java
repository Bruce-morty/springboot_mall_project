package top.philxin.model.WxHomeModel;

import lombok.Data;
import top.philxin.model.Goods;

@Data
public class GrouponList {
    private Double groupon_price;

    private Integer groupon_member;

    private Goods goods;
}
