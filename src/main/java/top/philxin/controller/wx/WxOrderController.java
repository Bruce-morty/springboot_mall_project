package top.philxin.controller.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
