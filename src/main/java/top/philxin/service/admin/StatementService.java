package top.philxin.service.admin;


import top.philxin.model.responseModel.StatementModel.StatementGoodBean;
import top.philxin.model.responseModel.StatementModel.StatementOrderBean;
import top.philxin.model.responseModel.StatementModel.StatementUserBean;

import java.util.List;

public interface StatementService {
    List<StatementUserBean> queryDateUsersNum();

    List<StatementOrderBean> queryDateOrder();

    List<StatementGoodBean> queryGoods();
}
