package top.philxin.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.GoodsMapper;
import top.philxin.mapper.GoodsProductMapper;
import top.philxin.mapper.OrderMapper;
import top.philxin.mapper.UserMapper;
import top.philxin.model.GoodsExample;
import top.philxin.model.GoodsProductExample;
import top.philxin.model.OrderExample;
import top.philxin.model.UserExample;
import top.philxin.model.responseModel.DashboardVo;
import top.philxin.service.admin.DashboardService;

/**
 * @ClassName: DashboardServiceImpl
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 10:59
 * @version: v1.0
 */
@Service
public class DashboardServiceImpl implements DashboardService{
    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public DashboardVo getDashboard() {
        DashboardVo dashboardVo = new DashboardVo();
        int goodsTotal = (int) goodsMapper.countByExample(new GoodsExample());
        int userTotal = (int) userMapper.countByExample(new UserExample());
        int productTotal = (int) goodsProductMapper.countByExample(new GoodsProductExample());
        int orderTotal = (int) orderMapper.countByExample(new OrderExample());
        dashboardVo.setGoodsTotal(goodsTotal);
        dashboardVo.setUserTotal(userTotal);
        dashboardVo.setProductTotal(productTotal);
        dashboardVo.setOrderTotal(orderTotal);
        return dashboardVo;
    }
}
