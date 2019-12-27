package top.philxin.service.impl;


import com.github.pagehelper.PageHelper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.philxin.mapper.CouponMapper;
import top.philxin.model.Ad;
import top.philxin.model.AdExample;
import top.philxin.model.Coupon;
import top.philxin.model.CouponExample;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.service.Generalize_couponService;

import java.util.List;


@Service
public class Generalize_couponServiceImpl implements Generalize_couponService {

@Autowired
    CouponMapper couponMapper;
    @Override
    public List<Coupon> queryCoupon(PageHelperVo pageHelperVo,String name,Integer type,Integer status) {
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        CouponExample couponExample=new CouponExample();
        CouponExample.Criteria criteria = couponExample.createCriteria();
        couponExample.setOrderByClause(pageHelperVo.getSort()+" "+pageHelperVo.getOrder());
        if(name!=null&&name.length()!=0)
        {
            criteria.andNameLike("%"+name+"%");
        }
      if(type!=null)
      {
          criteria.andTypeEqualTo(type.shortValue());
      }
      if(status!=null)
      {
          criteria.andStatusEqualTo(status.shortValue());
      }
        List<Coupon> coupons = couponMapper.selectByExample(couponExample);

        return coupons;

    }

}
