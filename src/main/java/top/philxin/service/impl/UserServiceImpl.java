package top.philxin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.UserMapper;
import top.philxin.model.User;
import top.philxin.model.UserExample;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.service.UserService;

import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 16:38
 * @version: v1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    BaseDataVo baseDataVo;

    /**
     * 获取用户信息 + 模糊查询
     * @param pageHelperVo
     * @param username
     * @param mobile
     * @return
     */
    @Override
    public BaseDataVo queryUsers(PageHelperVo pageHelperVo,String username, String mobile) {
        // 开启分页
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        userExample.setOrderByClause(pageHelperVo.getSort() + " " + pageHelperVo.getOrder());
        if(username != null && username.length() != 0) {
            criteria.andUsernameLike("%" + username + "%");
        }
        if(mobile != null && mobile.length() != 0) {
            criteria.andMobileLike("%" + mobile + "%");
        }
        List<User> userList = userMapper.selectByExample(userExample);
        // 存入 items
        baseDataVo.setItems(userList);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        long total = pageInfo.getTotal();
        // 存入 totals
        baseDataVo.setTotal((int)total);
        return baseDataVo;
    }
}
