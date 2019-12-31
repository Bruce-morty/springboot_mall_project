package top.philxin.model.OrderModel;

import lombok.Data;
import top.philxin.model.OrderGoods;

@Data
public class OrderList {
    Integer id;
    HandleOption handleOption;
    String orderStatusText;
    Boolean isGroupin;
    String orderSn;
    Double actualPrice;
    OrderGoods[] goodsList;
}
