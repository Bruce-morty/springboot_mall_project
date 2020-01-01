package top.philxin.controller.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.Coupon;
import top.philxin.model.GeneralizeModel.PageVo;
import top.philxin.model.Goods;
import top.philxin.model.GrouponRules;
import top.philxin.model.Topic;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.admin.Generalize_couponService;
import top.philxin.service.admin.Generalize_topicService;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx")
public class WxGeneralizeController {

    /**
     *  1专题模块
     */

    /**
     * 专题列表
     */
    @Autowired
    Generalize_topicService topicService;

    @RequestMapping("topic/list")
    public BaseRespVo getCoupon(PageVo pageVo) {

        Map map = topicService.WxQueryTopic(pageVo);

        return BaseRespVo.success(map);
    }

    /**
     * 通过ID查询专题的详情
     * @param id
     * @return
     */
    @RequestMapping("topic/detail")
    public BaseRespVo getTopicDetail(Integer id)
    {
      Map map=  topicService.WxQueryTopicById(id);
      return BaseRespVo.success(map);
    }

    /**
     * 通过ID获取 相关专题
     */
   @RequestMapping("topic/related")
    public BaseRespVo getRelatedTopic(Integer id)
   {
       List<Topic> topics = topicService.WxQueryRelatedTopics(id);
       return BaseRespVo.success(topics);
   }

    /**
     * 2.优惠券模块
     */

    @Autowired
    Generalize_couponService couponService;

    /**
     * 领取优惠券
     */

    @RequestMapping("coupon/receive")
    public BaseRespVo receiveCoupon(@RequestBody  Map map)
    {
          BaseRespVo baseRespVo= couponService.receiveCoupon(map);

        return baseRespVo;
    }

    /**
     * 我的优惠券列表
     */
    @RequestMapping("coupon/mylist")
    public BaseRespVo getCouponByStatus(PageVo pageVo,Integer status)
    {
        Map map=  couponService.queryCouponByStatus(pageVo,status);
        if(map.containsKey("noUserId"))
        {
            return BaseRespVo.error(501,"请登录");
        }
        return BaseRespVo.success(map);
    }

    /**
     *  优惠券列表
     */
    @RequestMapping("coupon/list")
    public BaseRespVo getCouponList(PageVo pageVo)
    {
        Map map=  couponService.queryCoupons(pageVo);

        return BaseRespVo.success(map);
    }

    /**
     * 当前订单可以使用的优惠券
     */
    @RequestMapping("coupon/selectlist")
    public BaseRespVo getCoupons(Integer cartId,Integer grouponRulesId )
    {
        Map map=  couponService.selectCoupons(cartId,grouponRulesId);
        if(map.containsKey("noUserId"))
        {
            return BaseRespVo.error(501,"请登录");
        }
        return BaseRespVo.success(map.get("data"));
    }


}
