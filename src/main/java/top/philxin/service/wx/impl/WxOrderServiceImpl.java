package top.philxin.service.wx.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.CommentMapper;
import top.philxin.mapper.OrderGoodsMapper;
import top.philxin.mapper.OrderMapper;
import top.philxin.model.Comment;
import top.philxin.model.Order;
import top.philxin.model.OrderGoods;
import top.philxin.model.OrderGoodsExample;
import top.philxin.model.WxCartModel.CheckOutVo;
import top.philxin.model.WxOrderModel.HandleOption;
import top.philxin.service.wx.WxOrderService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WxOrderServiceImpl implements WxOrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    CommentMapper commentMapper;

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

    /**
     * 此方法为支付订单的具体实现
     * 更新状态码为201,增加paytime和updatetime
     * @param orderId
     */
    //此方法需扣除库存，逻辑未做
    @Override
    public void prepayOrder(Integer orderId) {
        Date date = new Date();
        orderMapper.prepayOrder(orderId,date);
    }

    /**
     * 此方法为用户取消订单，此时订单未付款
     * 更新状态码为102,增加endtime和updatetime
     * @param orderId
     */
    @Override
    public void cancelOrder(Integer orderId) {
        Date date = new Date();
        orderMapper.cancelOrder(orderId,date);
    }

    //此方法需返还库存，逻辑未做
    /**
     * 此方法为用户退款取消订单
     * 更新状态码为202,增加endtime和updatetime
     * @param orderId
     */
    @Override
    public void refundOrder(Integer orderId) {
        Date date = new Date();
        orderMapper.refundOrder(orderId,date);
    }

    /**
     * 修改状态码为401
     * 增加updatetime和endtime
     * @param orderId
     */
    @Override
    public void confirmOrder(Integer orderId) {
        Date date = new Date();
        orderMapper.confrimOrder(orderId,date);
    }

    /**
     * 此方法用于根据orderId和goodsId获取orderGoods
     * @param orderId
     * @param goodsId
     * @return
     */
    @Override
    public OrderGoods getOrderGoods(Integer orderId, Integer goodsId) {
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        orderGoodsExample.createCriteria().andDeletedEqualTo(false).andOrderIdEqualTo(orderId).andGoodsIdEqualTo(goodsId);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);
        return orderGoods.get(0);
    }

    /**
     * 此方法用于对购买商品进行评论的具体实现
     * @param comment
     */
    @Override
    public void commentOrder(Comment comment) {
        Integer orderGoodsId = comment.getOrderGoodsId();
        OrderGoods orderGoods = orderGoodsMapper.selectByPrimaryKey(orderGoodsId);
        comment.setValueId(orderGoods.getGoodsId());
        //订单商品评论，设置type为3
        comment.setType((byte) 3);
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        comment.setUserId(userId);
        comment.setAddTime(new Date());
        comment.setUpdateTime(new Date());
        comment.setDeleted(false);
        commentMapper.insert(comment);
        //修改orderGoods中的comments为其在数据库中的编号，让商品不可再次被评论
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        orderGoodsExample.createCriteria().andIdEqualTo(orderGoodsId);
        orderGoods.setComment(comment.getId());
        orderGoodsMapper.updateByExampleSelective(orderGoods,orderGoodsExample);
    }

    /**
     * 此方法为提交订单的具体实现
     * @param message
     * @return
     */
    @Override
    public Map submitOrder(String message) {
        CheckOutVo checkoutData = (CheckOutVo) SecurityUtils.getSubject().getSession().getAttribute("checkoutData");
        Order order = new Order();
        //封装order的数据
        order.setUserId(checkoutData.getCheckedAddress().getUserId());
        //生成订单编号
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddss");
        String dateString = formatter.format(new Date());
        dateString = dateString + UUID.randomUUID().toString().replace("-","").substring(0,5);
        order.setOrderSn(dateString);
        order.setOrderStatus((short) 101);
        order.setConsignee(checkoutData.getCheckedAddress().getName());
        order.setMobile(checkoutData.getCheckedAddress().getMobile());
        order.setAddress(checkoutData.getCheckedAddress().getAddress());
        order.setMessage(message);
        order.setGoodsPrice(checkoutData.getGoodsTotalPrice());
        order.setFreightPrice(checkoutData.getFreightPrice());
        order.setCouponPrice(checkoutData.getCouponPrice());
        //用户积分减免，暂时设为0
        order.setIntegralPrice(BigDecimal.valueOf(0));
        order.setGrouponPrice(checkoutData.getGrouponPrice());
        order.setOrderPrice(checkoutData.getOrderTotalPrice());
        order.setActualPrice(checkoutData.getActualPrice());
        order.setAddTime(new Date());
        order.setUpdateTime(new Date());
        order.setDeleted(false);
        //待评价商品的数量，这里存的种类
        order.setComments((short) checkoutData.getCheckedGoodsList().size());
        orderMapper.insert(order);
        Map map = new HashMap();
        map.put("orderId",order.getId());
        return map;
    }
}
