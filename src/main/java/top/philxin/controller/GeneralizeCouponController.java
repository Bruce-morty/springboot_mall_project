package top.philxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.*;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.Generalize_AdService;
import top.philxin.service.Generalize_couponService;
import top.philxin.service.Generalize_grouponService;
import top.philxin.service.Generalize_topicService;

import java.lang.System;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GeneralizeCouponController {

    /**
     *  2.优惠券模块
     */
    /**
     * 按条件查询优惠券
     * @return
     */
    @Autowired
    Generalize_couponService couponService;
    @RequestMapping("admin/coupon/list")
    public BaseRespVo getCoupon(PageHelperVo pageHelperVo,String name,Integer type,Integer status )
   {
       Map map = couponService.queryCoupon(pageHelperVo, name, type, status);

       return BaseRespVo.success(map);
   }
    /**
     * 优惠券详情
     */
    @RequestMapping("admin/coupon/read")
    public BaseRespVo getCouponDetail(Integer id)
    {
        Coupon coupon = couponService.queryCouponById(id);
       return BaseRespVo.success(coupon);
    }
    /**
     * 优惠券所发送的用户
     */
    @RequestMapping("admin/coupon/listuser")
    public BaseRespVo getCouponUser(PageHelperVo pageHelperVo,Integer couponId,Integer userId,Integer status)
    {
        Map map= couponService.queryCouponUser(pageHelperVo, couponId, userId, status);
       return BaseRespVo.success(map);
    }
    /**
     * 更新编辑优惠券
     */
    @Transactional
    @RequestMapping("admin/coupon/update")
    public  BaseRespVo getCouponUpdate(@RequestBody Coupon coupon)
    {
        int i = couponService.updateCoupon(coupon);
        Coupon coupon1 = couponService.queryCouponById(coupon.getId());

       return BaseRespVo.success(coupon1);
    }
    /**
     * 添加优惠券
     */
    @Transactional
    @RequestMapping("admin/coupon/create")

    public BaseRespVo CreateCoupon(@RequestBody Coupon coupon)
    {
         couponService.addCoupon(coupon);
        Coupon coupon1 = couponService.queryCouponById(coupon.getId());
        return BaseRespVo.success(coupon1);
    }
    /**
     * 删除优惠券
     */

    @RequestMapping("admin/coupon/delete")
    public BaseRespVo deleteCoupon(@RequestBody Coupon coupon)
    {
        couponService.deleteCoupon(coupon);
        return BaseRespVo.success();
    }

    /**
     *   3.专题模块
     *
     */
    /**
     * 按条件查询专题
     */
  @Autowired
    Generalize_topicService topicService;
    @RequestMapping("admin/topic/list")
    public BaseRespVo getTopic(PageHelperVo pageHelperVo,String title,String subtitle)
    {


        Map map = topicService.queryTopic(pageHelperVo, title, subtitle);

        return BaseRespVo.success(map);
    }

    /**
     * 增加专题
     */
    @Transactional
 @RequestMapping("admin/topic/create")
    public BaseRespVo addTopic(@RequestBody Topic topic)
    {

        if(!"".equals(topic.getContent())&&topic.getPicUrl()!=null&&topic.getPrice()!=null&&topic.getReadCount()!=null) {

            Topic topic1 = topicService.addTopic(topic);
            return BaseRespVo.success(topic1);
        }
        return BaseRespVo.error(401,"参数不对");
    }
/**
 * 更新专题
 */
@Transactional
@RequestMapping("admin/topic/update")
  public BaseRespVo updateTopic(@RequestBody Topic topic)
{
    if(topic.getReadCount()!=null&&topic.getPrice()!=null)
    {
        Topic topic1 = topicService.updateTopic(topic);
        return BaseRespVo.success(topic1);
    }
    return BaseRespVo.error(401,"参数不对");
}

    /**
     * 删除专题
     */
    @RequestMapping("admin/topic/delete")
    public BaseRespVo deleteTopic(@RequestBody Topic topic)
    {
        topicService.deleteTopic(topic);
       return BaseRespVo.success();
    }

    /**
     *  4. 团购模块
     */

    /**
     * 显示所有商品团购规则，以及按商品ID查询
     */

    @Autowired
    Generalize_grouponService grouponService;
    @RequestMapping("admin/groupon/list")
    public BaseRespVo getGroupOn(PageHelperVo pageHelperVo,Integer goodsId)
    {
      Map map = grouponService.queryGroupon(pageHelperVo, goodsId);

        return BaseRespVo.success(map);

    }

    /**
     * 商品团购规则编辑
     */
    @Transactional
    @RequestMapping("admin/groupon/update")
    public BaseRespVo updateGroupOn(@RequestBody GrouponRules grouponRules)
    {
        if (grouponRules.getDiscount()!=null && grouponRules.getDiscountMember()!=null){

           Goods hasGood = grouponService.selectGoodsIs(grouponRules);
           if (hasGood != null) {
               grouponService.updateGroupon(grouponRules);
               return BaseRespVo.success();
           }
           return BaseRespVo.error(402,"参数值不对");
       }
        return BaseRespVo.error(401, "参数不对");

    }
    /**
     * 商品团购规则增加
     */
    @Transactional
    @RequestMapping("admin/groupon/create")
    public BaseRespVo createGroupon(@RequestBody GrouponRules grouponRules)
    {

           if (grouponRules.getDiscount()!=null && grouponRules.getDiscountMember()!=null) {

               Goods goodsIs = grouponService.selectGoodsIs(grouponRules);
               if (goodsIs != null) {
                   grouponRules.setGoodsName(goodsIs.getName());
                   grouponRules.setPicUrl(goodsIs.getPicUrl());
                   GrouponRules grouponRules1 = grouponService.insertGroupon(grouponRules);
                   return BaseRespVo.success(grouponRules1);
               }

               return BaseRespVo.error(402,"参数值不对");

           }
           return BaseRespVo.error(401, "参数不对");

    }
    /**
     * 删除商品团购规则
     */
    public BaseRespVo deleteGroupon(@RequestBody GrouponRules grouponRules)
    {

           grouponService.deleteGroupon(grouponRules);
          return BaseRespVo.success();

    }



    /**
     * 5.团购活动模块
     *
     * 获取订单商品对应的团购规则以及按商品Id查询对应的规则
     */

    @RequestMapping("admin/groupon/listRecord")
    public BaseRespVo getOrderGoodsGrouponRule(PageHelperVo pageHelperVo,Integer goodsId)
    {

        Map map = grouponService.queryOrderGoodsGrouponRule(pageHelperVo, goodsId);
        return BaseRespVo.success(map);
    }



}
