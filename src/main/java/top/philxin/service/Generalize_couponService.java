package top.philxin.service;

import top.philxin.model.Coupon;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;

import java.util.List;

public interface Generalize_couponService {
    List<Coupon> queryCoupon(PageHelperVo pageHelperVo,String name,Integer type,Integer status);
}
