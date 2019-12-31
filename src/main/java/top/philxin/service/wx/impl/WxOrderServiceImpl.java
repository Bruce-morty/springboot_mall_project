package top.philxin.service.wx.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.OrderGoodsMapper;
import top.philxin.mapper.OrderMapper;
import top.philxin.model.Order;
import top.philxin.model.OrderGoods;
import top.philxin.model.OrderGoodsExample;
import top.philxin.model.WxOrderModel.HandleOption;
import top.philxin.service.wx.WxOrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxOrderServiceImpl implements WxOrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    /**
     * 此方法为根据状态获取订单列表的具体实现
     *  0表示全部，1表示待付款，2表示待发货，3表示待收货，4表示待评价
     * @return
     */
    @Override
    public Map getOrderListByStatus(Integer showType,Integer page, Integer size) {
        //开启分页
        PageHelper.startPage(page,size);
        //取出session中的userId
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        //查询订单
        List<Order> orderLists = orderMapper.selectByStatus(showType,userId);
        //设置HandleOption
        for (Order orderList : orderLists) {
            setHandleOption(orderList);

        }
        //封入数据
        PageInfo<Order> pageInfo = new PageInfo<>(orderLists);
        Map map = new HashMap();
        map.put("data",orderLists);
        map.put("count",pageInfo.getTotal());
        map.put("totalPages",pageInfo.getPages());
        return map;
    }

    /**
     * 此方法为设置HandleOption的具体实现
     * @param order
     */
    private void setHandleOption(Order order) {
        Short orderStatus = order.getOrderStatus();
        //新建handleOption，此时默认所有值为false
        HandleOption handleOption = new HandleOption();

        //根据orderStaus设定用户可进行操作的功能
        switch (orderStatus){
            //用户未付款，可以付款、申请取消订单
            case 101:
                handleOption.setPay(true);
                handleOption.setCancel(true);
                order.setOrderStatusText("未付款");
                break;

            //用户取消订单，可以重新购买
            case 102:
                handleOption.setRebuy(true);
                order.setOrderStatusText("用户取消");
                break;

            //系统取消，可以重新购买
            case 103:
                handleOption.setRebuy(true);
                order.setOrderStatusText("系统取消");
                break;

            //用户已经付款，可以申请退款、重新购买
            case 201:
                handleOption.setRebuy(true);
                handleOption.setRefund(true);
                order.setOrderStatusText("已付款");
                break;

            //用户申请退款，无法进行操作
            case 202:
                order.setOrderStatusText("申请退款");
                break;

            //订单已退款，用户可以再次购买、删除订单
            case 203:
                handleOption.setRebuy(true);
                handleOption.setDelete(true);
                order.setOrderStatusText("已退款");
                break;

            //商家已发货，用户可以申请退款、重新购买、确认收货
            case 301:
                handleOption.setRebuy(true);
                handleOption.setRefund(true);
                handleOption.setConfirm(true);
                order.setOrderStatusText("已发货");
                break;

            //用户收货，可以评论、删除订单、可以重新购买
            case 401:
                handleOption.setComment(true);
                handleOption.setDelete(true);
                handleOption.setRebuy(true);
                order.setOrderStatusText("用户收货");
                break;

            //系统收货，可以评论、删除订单、可以重新购买
            case 402:
                handleOption.setComment(true);
                handleOption.setDelete(true);
                handleOption.setRebuy(true);
                order.setOrderStatusText("系统收货");
                break;
        }
        order.setHandleOption(handleOption);
    }

    /**
     * 此方法为根据订单状态获得订单详情的具体实现
     * @param orderId
     * @return
     */
    @Override
    public Map getOrderDetail(Integer orderId) {
        //查询orderInfo
        Order order = orderMapper.selectByPrimaryKey(orderId);
        setHandleOption(order);
        //查询orderGoods
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        orderGoodsExample.createCriteria().andOrderIdEqualTo(order.getId()).andDeletedEqualTo(false);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);
        //封装数据
        Map map = new HashMap();
        map.put("orderInfo",order);
        map.put("orderGoods",orderGoods);
        return map;
    }

    /**
     * 此方法为删除订单的具体实现，即将deleted设置为1
     * @param orderId
     */
    @Override
    public void deleteOrder(Integer orderId) {
        orderMapper.deleteOrder(orderId);
    }
}
