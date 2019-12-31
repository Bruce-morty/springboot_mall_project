package top.philxin.service.wx.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.*;
import top.philxin.model.*;
import top.philxin.service.wx.WxHomeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxHomeServiceImpl implements WxHomeService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    AdMapper adMapper;
    @Autowired
    BrandMapper brandMapper;

    /**
     * 此方法为获得小程序首页的具体实现
     * @return
     */
    @Override
    public Map homeIndex() {
        Map map = new HashMap();
        //构建newGoodsList并封装入bean中，查询条件为deleted为0且isnew为1
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIsNewEqualTo(true).andDeletedEqualTo(false);
        List<Goods> newGoodsList = goodsMapper.selectByExample(goodsExample);
        map.put("newGoodsList",newGoodsList);

        //构建couponList并封装入bean中，查询条件为deleted为0
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andDeletedEqualTo(false).andDaysGreaterThanOrEqualTo((short) 0);
        List<Coupon> couponList = couponMapper.selectByExample(couponExample);
        map.put("couponList",couponList);

        //构建channel(category)并封装入bean中,只显示pid为0的数据
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andPidEqualTo(0).andDeletedEqualTo(false);
        List<Category> channel = categoryMapper.selectByExample(categoryExample);
        map.put("channel", channel);

        //构建banner(ad)并封装入bean中,enabled为true且deleted为false
        AdExample adExample = new AdExample();
        adExample.createCriteria().andEnabledEqualTo(true).andDeletedEqualTo(false);
        List<Ad> banner = adMapper.selectByExample(adExample);
        map.put("banner", banner);

        //构建brandList并封装入bean中，deleted为false
        BrandExample brandExample = new BrandExample();
        brandExample.createCriteria().andDeletedEqualTo(false);
        List<Brand> brandList = brandMapper.selectByExample(brandExample);
        map.put("brandList", brandList);
        return map;
    }

    /**
     * 此方法用户统计未被删除的goods件数
     * @return
     */
    @Override
    public Integer countGoods() {
        Integer count = goodsMapper.countGoods();
        return count;
    }
}
