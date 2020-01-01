package top.philxin.model;

import lombok.Data;
import top.philxin.model.WxOrderModel.HandleOption;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Integer id;

    private Integer userId;

    private String orderSn;

    private Short orderStatus;

    private String consignee;

    private String mobile;

    private String address;

    private String message;

    private BigDecimal goodsPrice;

    private BigDecimal freightPrice;

    private BigDecimal couponPrice;

    private BigDecimal integralPrice;

    private BigDecimal grouponPrice;

    private BigDecimal orderPrice;

    private BigDecimal actualPrice;

    private String payId;

    private Date payTime;

    private String shipSn;

    private String shipChannel;

    private Date shipTime;

    private Date confirmTime;

    private Short comments;

    private Date endTime;

    private Date addTime;

    private Date updateTime;

    private HandleOption handleOption;

    private String orderStatusText;

    private Boolean isGroupin = false;

    private List<OrderGoods> goodsList;

    private Boolean deleted;
}