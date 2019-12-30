package top.philxin.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.Statement_mapper.StatementMapper;
import top.philxin.model.responseModel.StatementModel.StatementGoodBean;
import top.philxin.model.responseModel.StatementModel.StatementOrderBean;
import top.philxin.model.responseModel.StatementModel.StatementUserBean;
import top.philxin.service.admin.StatementService;

import java.util.List;

/**
 * @ClassName:
 * @Description: zy
 * @author:
 * @date:
 * @version: v1.0
 */
@Service
public class StatementServiceImpl implements StatementService {

    @Autowired
    StatementMapper statementMapper;

    /**
     * 用户统计
     * @return
     */
    @Override
    public List<StatementUserBean> queryDateUsersNum() {
        List<StatementUserBean> DateUsers = statementMapper.selectDateUsers();
        return DateUsers;
    }

    /**
     * 订单统计
     * @return
     */
    @Override
    public List<StatementOrderBean> queryDateOrder() {
        List<StatementOrderBean> orderBeans = statementMapper.selectOrders();
        return orderBeans;
    }

    /**
     * 商品统计
     * @return
     */
    @Override
    public List<StatementGoodBean> queryGoods() {
        List<StatementGoodBean> goodBeans = statementMapper.selectGoods();
        return goodBeans;
    }
}
