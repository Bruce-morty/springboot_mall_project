package top.philxin.service.impl;


import com.github.pagehelper.PageHelper;
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

    @Override
    public Coupon queryCouponById(Integer id) {
        Coupon coupon = couponMapper.selectByPrimaryKey(id);
        return coupon;
    }

    @Autowired
    CouponUserMapper couponUserMapper;
    @Override
    public List<CouponUser> queryCouponUser(PageHelperVo pageHelperVo, Integer couponId, Integer userId, Integer status) {

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
        List<CouponUser> couponUsers = couponUserMapper.selectByExample(couponUserExample);
        return couponUsers;
    }

    @Override
    public int updateCoupon(Coupon coupon) {
        int i = couponMapper.updateByPrimaryKeySelective(coupon);
        return i;
    }

    @Override
    public int addCoupon(Coupon coupon) {
        int insert = couponMapper.insert(coupon);
        return insert;
    }

    @Override
    public int deleteCoupon(Coupon coupon) {
        int i =  couponMapper.deleteByUpdate(coupon);
        return i;
    }

}
