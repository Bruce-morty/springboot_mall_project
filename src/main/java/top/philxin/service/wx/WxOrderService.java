package top.philxin.service.wx;

import top.philxin.model.Comment;
import top.philxin.model.OrderGoods;

import java.util.Map;

public interface WxOrderService {
    Map getOrderListByStatus(Integer showType,Integer page, Integer size);

    Map getOrderDetail(Integer orderId);

    void deleteOrder(Integer orderId);

    void prepayOrder(Integer orderId);

    void cancelOrder(Integer orderId);

    void refundOrder(Integer orderId);

    void confirmOrder(Integer orderId);

    OrderGoods getOrderGoods(Integer orderId, Integer goodsId);

    void commentOrder(Comment comment);

    Map submitOrder(String message);
}
