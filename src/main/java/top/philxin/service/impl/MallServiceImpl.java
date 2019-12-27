package top.philxin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.*;
import top.philxin.model.*;
import top.philxin.model.MallModel.*;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.service.MallService;

import java.lang.System;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MallServiceImpl implements MallService {
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    /**
     * 获取全部的行政区域并以list的形式返回
     * @return
     */
    @Override
    public List<Region> getAllRegion() {
        List<Region> regionList = regionMapper.selectByTypeAndPid(1,0);
        for (Region region : regionList) {
            List<Region> cityList = regionMapper.selectByTypeAndPid(2,region.getId());
            region.setChildren(cityList);
            for (Region cities : cityList) {
                cities.setChildren(regionMapper.selectByTypeAndPid(3,cities.getId()));
            }
        }
        return regionList;
    }

    /**
     * 此方法根据name的模糊查询和id的精确查询得到制造商信息
     * @param brandCondition
     * @return
     */
    @Override
    public BaseDataVo<Brand> getBrandListByPage(BrandCondition brandCondition) {
        PageHelper.startPage(brandCondition.getPage(),brandCondition.getLimit());
        BrandExample brandExample = new BrandExample();
        BrandExample.Criteria criteria = brandExample.createCriteria();
        //如果id不为空，精确查询id
        if(brandCondition.getId() != null){
            criteria.andIdEqualTo(brandCondition.getId());
        }
        //如果name不为空，模糊查询name
        if(brandCondition.getName() != null) {
            criteria.andNameLike("%" + brandCondition.getName() + "%");
        }
        //设置deleted为false，即未被删除的品牌商
        criteria.andDeletedEqualTo(false);
        //得到brandList并封入data中返回。
        List<Brand> brands = brandMapper.selectByExample(brandExample);
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        BaseDataVo<Brand> baseDataVo = new BaseDataVo<>();
        baseDataVo.setItems(brands);
        baseDataVo.setTotal((int) pageInfo.getTotal());
        return baseDataVo;
    }

    /**
     * 此方法为更新品牌商信息的具体实现
     * @param brand
     * @return
     */
    @Override
    public Brand updateBrand(Brand brand) {
        // new Date()为获取当前系统时间,更新updateTime
        brand.setUpdateTime(new Date());
        //更改数据中的品牌商信息
        brandMapper.updateByPrimaryKeySelective(brand);
        //将更改后的品牌商查出并返回
        Brand newBrand = brandMapper.selectByPrimaryKey(brand.getId());
        return newBrand;
    }

    /**
     * 此方法为删除品牌商的具体实现
     * 将数据库中的deleted值改为1
     * @param id
     */
    @Override
    public void deleteBrand(Integer id) {
        brandMapper.deleteBrandById(id);
    }

    /**
     * 此方法用于获得全部的商品类目
     * @return
     */
    @Override
    public List<Category> getCategoryList() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andLevelEqualTo("L1").andDeletedEqualTo(false);
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        //封装二级类目
        for (Category category : categories) {
            categoryExample.clear();
            categoryExample.createCriteria().andPidEqualTo(category.getId()).andDeletedEqualTo(false);
            category.setChildren(categoryMapper.selectByExample(categoryExample));
        }
        return categories;
    }

    /**
     * 此方法用户获得某个level的商品类目
     * @param level
     * @return
     */
    @Override
    public List<CategoryByLevel> getCategoryByLevel(String level) {
        return categoryMapper.selectByLevel(level);
    }

    /**
     * 此方法为删除商品类目的具体实现，假删，将deleted设为1
     *不论一级还是二级均直接设置
     * 由于二级显示时依赖于一级类目，故一级类目为1时，二级就算为0也无法获得
     * @param id
     */
    @Override
    public void deleteCategory(Integer id) {
        categoryMapper.deleteCategory(id);
    }

    /**
     * 此方法为更新商品类目的具体实现。
     * @param category
     */
    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    /**
     * 此方法为根据用户id，订单编号以及订单状态分页获得订单的具体实现
     *
     * @param orderCondition
     * @return
     */
    @Override
    public BaseDataVo<Order> getOrderListByPage(OrderCondition orderCondition) {
        //开启分页
        PageHelper.startPage(orderCondition.getPage(),orderCondition.getLimit());
        OrderExample orderExample = new OrderExample();
        //查询存在的订单，即deleted为0
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        //如果userId存在，精确查询
        if(orderCondition.getUserId() != null) {
            criteria.andUserIdEqualTo(orderCondition.getUserId());
        }
        //如果订单编号存在，精确查询
        if(orderCondition.getOrderSn() != null) {
            criteria.andOrderSnEqualTo(orderCondition.getOrderSn());
        }
        //如果订单状态存在，查询Array中全部订单状态的订单
        if(orderCondition.getOrderStatusArray() != null) {
            criteria.andOrderStatusIn(orderCondition.getOrderStatusArray());
        }
        List<Order> orders = orderMapper.selectByExample(orderExample);
        //封入数据
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        BaseDataVo<Order> baseDataVo = new BaseDataVo<>();
        baseDataVo.setTotal((int) pageInfo.getTotal());
        baseDataVo.setItems(orders);
        return baseDataVo;
    }

    /**
     * 此方法根据订单的id查询订单的详情
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getOrderDetail(int id) {
        Map<String, Object> map = new HashMap<>();
        Order order = orderMapper.selectByPrimaryKey(id);
        map.put("order",order);
        User user = userMapper.selectByPrimaryKey(order.getUserId());
        map.put("user",user);
        OrderGoodsExample example = new OrderGoodsExample();
        example.createCriteria().andDeletedEqualTo(false).andOrderIdEqualTo(id);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(example);
        map.put("orderGoods",orderGoods);
        return map;
    }

    /**
     * 此方法用于退款，将订单状态改为203
     * @param orderId
     * @param orderId
     */
    @Override
    public void refund(Integer orderId) {
        //此方法用于将指定id的订单改为指定状态，此处改为203
        orderMapper.updateOrderState(orderId,203);
    }

    /**
     * 此方法用于发货，将订单状态改为301
     * @param map
     */
    @Override
    public void ship( Map map) {
        //封装Order
        Order order = new Order();
        order.setShipTime(new Date());
        order.setId((Integer) map.get("orderId"));
        order.setShipChannel((String) map.get("shipChannel"));
        order.setShipSn((String) map.get("shipSn"));
        order.setOrderStatus((short) 301);
        //数据库中修改
        orderMapper.updateByPrimaryKeySelective(order);
    }

}
