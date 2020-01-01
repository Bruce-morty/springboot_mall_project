package top.philxin.service.wx.impl;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.*;
import top.philxin.model.*;
import top.philxin.model.WxHomeModel.FloorGoods;
import top.philxin.model.WxHomeModel.GrouponList;
import top.philxin.service.wx.WxHomeService;

import java.math.BigDecimal;
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
    @Autowired
    TopicMapper topicMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    /**
     * 此方法为获得小程序首页的具体实现
     * @return
     */
    @Override
    public Map homeIndex() {
        Map map = new HashMap();
        //构建newGoodsList并封装入bean中，查询条件为deleted为0且isnew为1
        //显示只显示6个新品
        List<Goods> newGoodsList = goodsMapper.selectGoodsForHome();
        map.put("newGoodsList",newGoodsList);

        //构建couponList并封装入bean中，查询条件为deleted为0且status为0
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andDeletedEqualTo(false).andStatusEqualTo((short) 0).andTypeEqualTo((short) 0);
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
        //限制只显示6个品牌制造商
        List<Brand> brandList = brandMapper.selectBrandForHome();
        map.put("brandList", brandList);

        //构建topicList并封装入bean,deleted为false
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria().andDeletedEqualTo(false);
        List<Topic> topicList = topicMapper.selectByExample(topicExample);
        map.put("topicList",topicList);

        //构建grouponList并封装入bean,deleted为false
        List<GrouponList> grouponList = grouponRulesMapper.selectGrouponList();
        //此时groupon_price为优惠金额，循环更新
        for (GrouponList list : grouponList) {
            Double price = list.getGoods().getRetailPrice().doubleValue() - list.getGroupon_price();
            list.setGroupon_price(price);
        }
        map.put("grouponList",grouponList);

        //构建floorGoodsList并封装入bean(完整的category)
        //每个类别最多放4个
        List<FloorGoods> floorGoodsList = categoryMapper.selectFloorGoods();
        map.put("floorGoodsList",floorGoodsList);
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
