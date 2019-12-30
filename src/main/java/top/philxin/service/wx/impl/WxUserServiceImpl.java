package top.philxin.service.wx.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.UserMapper;
import top.philxin.model.User;
import top.philxin.model.UserExample;
import top.philxin.model.responseModel.WxUserModel.WxUserInfoVo;
import top.philxin.service.wx.WxUserService;

import java.util.List;

/**
 * @ClassName: WxUserServiceImpl
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/30 0030 20:22
 * @version: v1.0
 */
@Service
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    @Override
    public User getUserInfoByName(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        return users.get(0);
    }
}
