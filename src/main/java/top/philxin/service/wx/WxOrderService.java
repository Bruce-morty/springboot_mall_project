package top.philxin.service.wx;

import java.util.Map;

public interface WxOrderService {
    Map getOrderListByStatus(Integer showType,Integer page, Integer size);

    Map getOrderDetail(Integer orderId);

    void deleteOrder(Integer orderId);
}
