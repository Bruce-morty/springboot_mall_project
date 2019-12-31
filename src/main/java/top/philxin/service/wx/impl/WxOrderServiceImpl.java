package top.philxin.service.wx.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.OrderMapper;
import top.philxin.service.wx.WxOrderService;

import java.util.Map;

@Service
public class WxOrderServiceImpl implements WxOrderService {
    @Autowired
    OrderMapper orderMapper;

    /**
     * 此方法为根据状态获取订单列表的具体实现
     * @return
     */
    @Override
    public Map getOrderListByStatus(Map param) {
        return null;
    }
}
