package top.philxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.HashMap;
import java.util.List;

@RestController
public class Generalize_CouponController {

    /**
     *优惠券模块
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
      List<Coupon> couponList= couponService.queryCoupon(pageHelperVo,name,type,status);
       HashMap<Object, Object> map = new HashMap<>();
       map.put("items",couponList);
       map.put("total",couponList.size());
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
        List<CouponUser> couponUsers = couponService.queryCouponUser(pageHelperVo, couponId, userId, status);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("total",couponUsers.size());
        map.put("items",couponUsers);
       return BaseRespVo.success(map);
    }
    /**
     * 更新编辑优惠券
     */

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
     *   专题模块
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

        List<Topic> topics = topicService.queryTopic(pageHelperVo, title, subtitle);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("items",topics);
        map.put("total",topics.size());
        return BaseRespVo.success(map);
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
     *   团购模块
     */

    /**
     * 显示所有商品团购规则，以及按商品ID查询
     */

    @Autowired
    Generalize_grouponService grouponService;
    @RequestMapping("admin/groupon/list")
    public BaseRespVo getGroupOn(PageHelperVo pageHelperVo,Integer goodsId)
    {
        List<GrouponRules> grouponRules = grouponService.queryGroupon(pageHelperVo, goodsId);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("items",grouponRules);
        map.put("total",grouponRules.size());
        return BaseRespVo.success(map);

    }

    /**
     * 团购规则编辑
     */
    @RequestMapping("admin/groupon/update")
    public BaseRespVo updateGroupOn(@RequestBody GrouponRules grouponRules)
    {

        boolean sameGoodsIs = grouponService.isSameGoodsIs(grouponRules);
        if(sameGoodsIs)
        {
               grouponService.updateGroupon(grouponRules);
              return BaseRespVo.success();
        }
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("参数值不对");
        baseRespVo.setErrno(402);
        return baseRespVo;

    }

}
