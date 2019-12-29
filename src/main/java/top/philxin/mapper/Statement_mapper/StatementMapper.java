package top.philxin.mapper.Statement_mapper;

import top.philxin.model.responseModel.StatementModel.StatementGoodBean;
import top.philxin.model.responseModel.StatementModel.StatementOrderBean;
import top.philxin.model.responseModel.StatementModel.StatementUserBean;

import java.util.List;

/**
 * @ClassName:
 * @Description: zy
 * @author:
 * @date:
 * @version: v1.0
 */
public interface StatementMapper {

    List<StatementUserBean> selectDateUsers();

    List<StatementOrderBean> selectOrders();

    List<StatementGoodBean> selectGoods();
}
