package top.philxin.service;

import top.philxin.model.responseModel.StatementModel.StatementBean;

import java.util.List;

public interface StatementService {
    List<StatementBean> queryDateUsersNum();
}
