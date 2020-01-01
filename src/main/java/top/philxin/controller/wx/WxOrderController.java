package top.philxin.controller.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.mapper.OrderGoodsMapper;
import top.philxin.model.Comment;
import top.philxin.model.OrderGoods;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.wx.WxOrderService;

import java.util.Map;

@RestController
@RequestMapping("wx")
public class WxOrderController {
    @Autowired
    WxOrderService wxOrderService;

    /**
     * 此方法用于根据订单状态分页显示订单
     * 0表示全部，1表示待付款，2表示待发货，3表示待收货，4表示待评价
     * @return
     */
    //团购逻辑未知，目前将isGroupin全部设置为false
    @RequestMapping("order/list")
    public BaseRespVo getOrderList(Integer showType,Integer page, Integer size){
        Map map = wxOrderService.getOrderListByStatus(showType, page, size);
        return BaseRespVo.success(map);
    }

    /**
     * 此方法根据订单状态获得订单详情
     * @param orderId
     * @return
     */
    @RequestMapping("order/detail")
    public BaseRespVo getOrderDetail(Integer orderId){
        Map map = wxOrderService.getOrderDetail(orderId);
        return BaseRespVo.success(map);
    }

    /**
     * 此方法用于删除订单
     * @param map
     * @return
     */
    @RequestMapping("order/delete")
    public BaseRespVo deleteOrder(@RequestBody Map map){
        Integer orderId = (Integer) map.get("orderId");
        wxOrderService.deleteOrder(orderId);
        return BaseRespVo.success();
    }

    //此方法需要扣除库存，逻辑未做
    /**
     * 此方法用于订单支付
     * @param map
     * @return
     */
    @RequestMapping("/order/prepay")
    public BaseRespVo prepayOrder(@RequestBody Map map) {
        Integer orderId = (Integer) map.get("orderId");
        wxOrderService.prepayOrder(orderId);
        return BaseRespVo.success();
    }

    /**
     * 此方法用于取消订单（此时订单未付款，勿需返还库存）
     * @param map
     * @return
     */
    @RequestMapping("order/cancel")
    public BaseRespVo cancelOrder(@RequestBody Map map) {
        Integer orderId = (Integer) map.get("orderId");
        wxOrderService.cancelOrder(orderId);
        return BaseRespVo.success();
    }

    //此处需要返还库存，逻辑还未做

    /**
     * 此方法为用户退款取消了订单，由于用户已付款，需要返还库存
     * @param map
     * @return
     */
    @RequestMapping("order/refund")
    public BaseRespVo refundOrder(@RequestBody Map map) {
        Integer orderId = (Integer) map.get("orderId");
        wxOrderService.refundOrder(orderId);
        return BaseRespVo.success();
    }

    /**
     * 此方法为用户确认收货,修改状态码为401
     * 增加updatetime和endtime
     * @param map
     * @return
     */
    @RequestMapping("order/confrim")
    public BaseRespVo confrimOrder(@RequestBody Map map) {
        Integer orderId = (Integer) map.get("orderId");
        wxOrderService.confrimOrder(orderId);
        return BaseRespVo.success();
    }

    /**
     * 此方法为评论前获取orderGoods
     * @param orderId
     * @param goodsId
     * @return
     */
    @RequestMapping("order/goods")
    public BaseRespVo getOrderGoods(Integer orderId, Integer goodsId) {
        OrderGoods orderGoods = wxOrderService.getOrderGoods(orderId, goodsId);
        return BaseRespVo.success(orderGoods);
    }

    /**
     * 此方法为评论前获取orderGoods
     * @param comment
     * @return
     */
    @RequestMapping("order/comment")
    public BaseRespVo commentOrder(@RequestBody Comment comment) {
        wxOrderService.commentOrder(comment);
        return BaseRespVo.success();
    }
}
