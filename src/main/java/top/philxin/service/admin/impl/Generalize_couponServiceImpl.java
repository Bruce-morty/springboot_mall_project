package top.philxin.service.admin.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.CouponMapper;
import top.philxin.mapper.CouponUserMapper;
import top.philxin.model.Coupon;
import top.philxin.model.CouponExample;
import top.philxin.model.CouponUser;
import top.philxin.model.CouponUserExample;
import top.philxin.model.GeneralizeModel.PageVo;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.admin.Generalize_couponService;

import java.util.*;


@Service
public class Generalize_couponServiceImpl implements Generalize_couponService {

    /**
     * 后台 处理优惠券
     */
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
        Coupon coupon1 = couponMapper.selectByPrimaryKey(coupon.getId());
        if(coupon.getDays()!=null&&coupon.getDays()!=0)
        {
            coupon1.setStartTime(null);
            coupon1.setEndTime(null);
            couponMapper.updateByPrimaryKeySelective(coupon1);
        }

        if(coupon.getDays()==null)
        {
            coupon1.setDays(null);
            couponMapper.updateByPrimaryKeySelective(coupon1);
        }
        int i = couponMapper.updateByPrimaryKeySelective(coupon);
        return i;
    }

    @Override
    public Coupon addCoupon(Coupon coupon) {
        if(coupon.getType()==2) {
            String string = UUID.randomUUID().toString();
            coupon.setCode(string.substring(0, 5));
        }
        coupon.setAddTime(new Date());
        coupon.setDeleted(false);

        couponMapper.insert(coupon);
        List<Coupon> coupons = couponMapper.selectByExample(new CouponExample());
        Coupon coupon1 = coupons.get(coupons.size() - 1);
        return coupon1;


    }

    @Override
    public int deleteCoupon(Coupon coupon) {
        int i =  couponMapper.deleteByUpdate(coupon);
        return i;
    }


    /**
     * 前台处理优惠券
     *
     */


    //获取优惠券
    @Override
    public BaseRespVo receiveCoupon(Map map) {
        Integer couponId = (Integer) map.get("couponId");
        Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
        //当前优惠券限制每个用户领取的数量
        Short limit = coupon.getLimit();

        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getSession().getAttribute("userId");

        if(userId==null)
        {
            return BaseRespVo.error(501,"请登录");
        }
        //查看用户领取优惠券的数量
        CouponUserExample couponUserExample = new CouponUserExample();
        CouponUserExample.Criteria criteria = couponUserExample.createCriteria();
        criteria.andCouponIdEqualTo(couponId);
        criteria.andUserIdEqualTo(userId);
        List<CouponUser> couponUsers = couponUserMapper.selectByExample(couponUserExample);
        //查看该优惠券被领取的数量
        CouponUserExample couponUserExample2 = new CouponUserExample();
        CouponUserExample.Criteria criteria2 = couponUserExample.createCriteria();
        criteria2.andCouponIdEqualTo(couponId);
        List<CouponUser> couponUsers2 = couponUserMapper.selectByExample(couponUserExample2);

        if(couponUsers2.size()==coupon.getTotal()&&coupon.getTotal()!=0)
        {
            return BaseRespVo.error(740,"优惠券已领取完");
        }
        if(limit==couponUsers.size()&&limit!=0)
        {
            return BaseRespVo.error(740,"超过领取的数量");
        }
        Date date=new Date();

     if(coupon.getDays()!=null)
     {
      Long betweenDate=(date.getTime()-coupon.getAddTime().getTime())/(60*60*24*1000);
         System.out.println(betweenDate);

      if(betweenDate>coupon.getDays())
      {
          coupon.setStatus((short) 2);
          return BaseRespVo.error(740, "优惠券已过期");
      }else
      {
          coupon.setStartTime(coupon.getAddTime());
          long startTime = coupon.getAddTime().getTime();
            long day= coupon.getDays()*24*60*60*1000;

          coupon.setEndTime(new Date(startTime+day));
      }


     }else {
         if (date.before(coupon.getEndTime())) {
             //优惠券过期状态号变为2
             coupon.setStatus((short) 2);
             return BaseRespVo.error(740, "优惠券已过期");
         }
     }


        insertCouponUser(couponId,coupon);
        return BaseRespVo.success();

    }

public  void insertCouponUser(Integer couponId,Coupon coupon)
{

    CouponUser couponUser1 = new CouponUser();
    Date startTime = coupon.getStartTime();
    Date endTime = coupon.getEndTime();
    Subject subject = SecurityUtils.getSubject();
    Integer userId = (Integer) subject.getSession().getAttribute("userId");

    couponUser1.setUserId(userId);
    couponUser1.setStartTime(startTime);
    couponUser1.setEndTime(endTime);
    couponUser1.setAddTime(new Date());
    couponUser1.setUpdateTime(new Date());
    couponUser1.setCouponId(couponId);
    couponUser1.setStatus(coupon.getStatus());
    couponUser1.setDeleted(false);
    couponUserMapper.insert(couponUser1);

}



    /**
     * 通过状态获取对应的优惠券
     */

    @Override
    public Map queryCouponByStatus(PageVo pageVo, Integer status) {
        PageHelper.startPage(pageVo.getPage(),pageVo.getSize());
        CouponUserExample couponUserExample = new CouponUserExample();
        CouponUserExample.Criteria criteria = couponUserExample.createCriteria();
        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getSession().getAttribute("userId");
            if(userId==null)
            {
                HashMap<Object, Object> map = new HashMap<>();

                map.put("noUserId",0);
                return map;
            }
        criteria.andUserIdEqualTo(userId);
        criteria.andStatusEqualTo(status.shortValue());
        List<CouponUser> couponUsers = couponUserMapper.selectByExample(couponUserExample);


        List<Coupon> coupons=new ArrayList<>();

        for (CouponUser couponUser : couponUsers) {

            Coupon coupon = couponMapper.selectByPrimaryKey(couponUser.getCouponId());

            if (coupon.getDays() != null) {

                   coupon.setStartTime(coupon.getAddTime());
                   long startTime = coupon.getAddTime().getTime();
                   long day = coupon.getDays() * 24 * 60 * 60 * 1000;
                   coupon.setEndTime(new Date(startTime + day));
               }
               coupons.add(coupon);
        }
        PageInfo<Coupon> couponPageInfo = new PageInfo<>(coupons);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data",coupons);
        map.put("count",couponPageInfo.getTotal());
        return map;
    }

    /**
     * 获取所有优惠券
     * @param pageVo
     * @return
     */
    @Override
    public Map queryCoupons(PageVo pageVo) {
        PageHelper.startPage(pageVo.getPage(),pageVo.getSize());
        CouponExample couponExample = new CouponExample();
        CouponExample.Criteria criteria = couponExample.createCriteria();
        criteria.andStatusEqualTo(Integer.valueOf(0).shortValue());
        List<Coupon> coupons = couponMapper.selectByExample(couponExample);
        PageInfo<Coupon> couponPageInfo = new PageInfo<>(coupons);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data",coupons);
        map.put("count",couponPageInfo.getTotal());
        return map;

    }

    /**
     * 查询可以使用的优惠券
     * @param cartId
     * @param grouponRulesId
     * @return
     */

    @Override
    public Map selectCoupons(Integer cartId, Integer grouponRulesId) {
        CouponUserExample couponUserExample = new CouponUserExample();
        CouponUserExample.Criteria criteria = couponUserExample.createCriteria();
        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getSession().getAttribute("userId");
        if(userId==null)
        {
            HashMap<Object, Object> map = new HashMap<>();

            map.put("noUserId",0);
            return map;
        }
        criteria.andUserIdEqualTo(userId);
        criteria.andStatusEqualTo((short)0);
        List<CouponUser> couponUsers = couponUserMapper.selectByExample(couponUserExample);


        List<Coupon> coupons=new ArrayList<>();

        for (CouponUser couponUser : couponUsers) {

            Coupon coupon = couponMapper.selectByPrimaryKey(couponUser.getCouponId());

            if (coupon.getDays() != null) {

                coupon.setStartTime(coupon.getAddTime());
                long startTime = coupon.getAddTime().getTime();
                long day = coupon.getDays() * 24 * 60 * 60 * 1000;
                coupon.setEndTime(new Date(startTime + day));
            }
            coupons.add(coupon);
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data",coupons);

        return map;
    }


}
