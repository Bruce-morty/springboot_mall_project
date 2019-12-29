package top.philxin.service;

import top.philxin.model.Coupon;
import top.philxin.model.CouponUser;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;

import java.util.List;
import java.util.Map;

public interface Generalize_couponService {
    Map queryCoupon(PageHelperVo pageHelperVo, String name, Integer type, Integer status);
    Coupon queryCouponById(Integer id);
Map queryCouponUser(PageHelperVo pageHelperVo,Integer couponId,Integer userId,Integer status);

    int updateCoupon(Coupon coupon);

    int addCoupon(Coupon coupon);

    int deleteCoupon(Coupon coupon);
}
