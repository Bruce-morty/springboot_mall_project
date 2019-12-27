package top.philxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.Statement_mapper.StatementDateUsersMapper;
import top.philxin.model.responseModel.StatementModel.StatementBean;
import top.philxin.service.StatementService;

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
    StatementDateUsersMapper statementDateUsersMapper;

    @Override
    public List<StatementBean> queryDateUsersNum() {
        List<StatementBean> DateUsers = statementDateUsersMapper.selectDateUsers();
        return DateUsers;
    }
}
