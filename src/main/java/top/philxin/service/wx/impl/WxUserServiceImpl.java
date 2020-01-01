package top.philxin.service.wx.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.OrderMapper;
import top.philxin.mapper.UserMapper;
import top.philxin.model.User;
import top.philxin.model.UserExample;
import top.philxin.model.responseModel.WxUserModel.WxUserIndexOrderStatusInfoVo;
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

    @Autowired
    OrderMapper orderMapper;

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

    /**
     * 获取用户个人中心主页
     * @param userId
     * @return
     */
    @Override
    public WxUserIndexOrderStatusInfoVo getUserIndexInfo(int userId) {
        int countUnpaid = 0;
        int countUnrecv = 0;
        int countUnship = 0;
        int countUncommnent = 0;
        List<Integer> orderStatusList = orderMapper.selectOrderStatusByUserId(userId);
        for (Integer status : orderStatusList) {
            if(status == 101) {
                countUnpaid++;
            }else if(status == 201) {
                countUnship++;
            }else if(status == 301) {
                countUnrecv++;
            }else if(status == 401 || status == 402) {
                countUncommnent++;
            }
        }
        WxUserIndexOrderStatusInfoVo wxUserIndexOrderStatusInfoVo = new WxUserIndexOrderStatusInfoVo();
        wxUserIndexOrderStatusInfoVo.setUnpaid(countUnpaid);
        wxUserIndexOrderStatusInfoVo.setUnrecv(countUnrecv);
        wxUserIndexOrderStatusInfoVo.setUnship(countUnship);
        wxUserIndexOrderStatusInfoVo.setUncommnet(countUncommnent);
        return wxUserIndexOrderStatusInfoVo;
    }
}
