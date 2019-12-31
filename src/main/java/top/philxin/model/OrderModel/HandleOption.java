package top.philxin.model.OrderModel;

import lombok.Data;

@Data
public class HandleOption {
    Boolean cancel;
    Boolean delete;
    Boolean pay;
    Boolean comment;
    Boolean confirm;
    Boolean refund;
    Boolean rebuy;
}
