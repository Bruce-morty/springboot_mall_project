package top.philxin.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.CouponMapper;
import top.philxin.mapper.CouponUserMapper;
import top.philxin.model.Coupon;
import top.philxin.model.CouponExample;
import top.philxin.model.CouponUser;
import top.philxin.model.CouponUserExample;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.service.Generalize_couponService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class Generalize_couponServiceImpl implements Generalize_couponService {

@Autowired
    CouponMapper couponMapper;
    @Override
    public Map queryCoupon(PageHelperVo pageHelperVo, String name, Integer type, Integer status) {
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
        criteria.andDeletedEqualTo(false);
        List<Coupon> coupons = couponMapper.selectByExample(couponExample);
        PageInfo<Coupon> couponPageInfo = new PageInfo<>(coupons);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("items",coupons);
        map.put("total",couponPageInfo.getTotal());
        return map;

    }

    @Override
    public Coupon queryCouponById(Integer id) {
        Coupon coupon = couponMapper.selectByPrimaryKey(id);
        return coupon;
    }

    @Autowired
    CouponUserMapper couponUserMapper;
    @Override
    public Map queryCouponUser(PageHelperVo pageHelperVo, Integer couponId, Integer userId, Integer status) {

        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());

        CouponUserExample couponUserExample = new CouponUserExample();
        CouponUserExample.Criteria criteria = couponUserExample.createCriteria();
        criteria.andCouponIdEqualTo(couponId);
        couponUserExample.setOrderByClause(pageHelperVo.getSort()+" "+pageHelperVo.getOrder());
        if(userId!=null)
        {
            criteria.andUserIdEqualTo(userId);
        }
        if(status!=null)
        {
            criteria.andStatusEqualTo(status.shortValue());
        }
        criteria.andDeletedEqualTo(false);
        List<CouponUser> couponUsers = couponUserMapper.selectByExample(couponUserExample);
        PageInfo<CouponUser> couponUserPageInfo = new PageInfo<>(couponUsers);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("total",couponUserPageInfo.getTotal());
        map.put("items",couponUsers);
        return map;
    }

    @Override
    public int updateCoupon(Coupon coupon) {
        coupon.setUpdateTime(new Date());
        int i = couponMapper.updateByPrimaryKeySelective(coupon);
        return i;
    }

    @Override
    public int addCoupon(Coupon coupon) {
        coupon.setAddTime(new Date());
        int insert = couponMapper.insert(coupon);
        return insert;
    }

    @Override
    public int deleteCoupon(Coupon coupon) {
        int i =  couponMapper.deleteByUpdate(coupon);
        return i;
    }

}
