package top.philxin.controller.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.WxOrderService;

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
    @RequestMapping("order/list")
    public BaseRespVo getOrderList(){
        Map map = wxOrderService.getOrderListByStatus();
        return BaseRespVo.success(map);

    }
}
