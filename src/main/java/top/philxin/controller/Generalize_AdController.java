package top.philxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.Ad;
import top.philxin.model.Coupon;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.Generalize_AdService;
import top.philxin.service.Generalize_couponService;

import java.util.HashMap;
import java.util.List;

@RestController
public class Generalize_AdController {

  @Autowired
  Generalize_AdService generalize_adService;

    /**
     *
     * 按条件查询广告消息
     * @param pageHelperVo
     * @param name
     * @param content
     * @return
     */
    @RequestMapping("admin/ad/list")
    public BaseRespVo getAd(PageHelperVo pageHelperVo,String name,String content)
    {
        List<Ad> ad = generalize_adService.getAd(pageHelperVo,name,content);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("items",ad);
        map.put("total",ad.size());
        return BaseRespVo.success(map);

    }

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
}
