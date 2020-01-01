package top.philxin.model.WxOrderModel;

import lombok.Data;

@Data
public class HandleOption {
    Boolean cancel = false;

    Boolean delete = false;

    Boolean pay = false;

    Boolean comment = false;

    Boolean confirm = false;

    Boolean refund = false;

    Boolean rebuy = false;
}
