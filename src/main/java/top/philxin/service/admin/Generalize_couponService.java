package top.philxin.service.admin;

import top.philxin.model.Coupon;
import top.philxin.model.CouponUser;
import top.philxin.model.GeneralizeModel.PageVo;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;

import java.util.List;
import java.util.Map;

public interface Generalize_couponService {
    Map queryCoupon(PageHelperVo pageHelperVo, String name, Integer type, Integer status);
    Coupon queryCouponById(Integer id);
Map queryCouponUser(PageHelperVo pageHelperVo,Integer couponId,Integer userId,Integer status);

    int updateCoupon(Coupon coupon);

    Coupon addCoupon(Coupon coupon);

    int deleteCoupon(Coupon coupon);

    BaseRespVo receiveCoupon(Map map);

    Map queryCouponByStatus(PageVo pageVo, Integer status);


    Map queryCoupons(PageVo pageVo);

    Map selectCoupons(Integer cartId, Integer grouponRulesId);
}
