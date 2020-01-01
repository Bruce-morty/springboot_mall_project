package top.philxin.service.wx.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.component.AliyunComponent;
import top.philxin.exception.CodeExpiredException;
import top.philxin.exception.CodeMessageException;
import top.philxin.exception.UnPairedCodeException;
import top.philxin.exception.UsernameExistException;
import top.philxin.mapper.FeedbackMapper;
import top.philxin.mapper.OrderMapper;
import top.philxin.mapper.UserMapper;
import top.philxin.mapper.UserRegisterCodeMapper;
import top.philxin.model.Feedback;
import top.philxin.model.User;
import top.philxin.model.UserExample;
import top.philxin.model.UserRegisterCode;
import top.philxin.model.requestModel.WxUserRegisterVo;
import top.philxin.model.responseModel.WxUserModel.WxUserIndexOrderStatusInfoVo;
import top.philxin.service.wx.WxUserService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    UserRegisterCodeMapper userRegisterCodeMapper;

    @Autowired
    AliyunComponent aliyunComponent;

    @Autowired
    FeedbackMapper feedbackMapper;

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

    @Override
    public User userRegister(WxUserRegisterVo wxUserRegisterVo) throws UnPairedCodeException, UsernameExistException, CodeExpiredException {
        // 获取当前时间
        Date date = new Date();
        // 判断验证码是否相同
        String mobile = wxUserRegisterVo.getMobile();
        List<UserRegisterCode> infos = userRegisterCodeMapper.selectInfoByMobile(mobile);
        UserRegisterCode info = infos.get(0);
        if(!wxUserRegisterVo.getCode().equals(info.getCode())) {
            throw new UnPairedCodeException("验证码错误");
        }
        if(info.getExpiredTime().before(date)) {
            throw new CodeExpiredException("验证码已过期");
        }
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(wxUserRegisterVo.getUsername());
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList.size() != 0) {
            throw new UsernameExistException("用户名已存在");
        }
        // 增加新用户
        User user = new User();
        user.setUsername(wxUserRegisterVo.getUsername());
        user.setPassword(wxUserRegisterVo.getPassword());
        user.setGender((byte) 0);
        user.setNickname(wxUserRegisterVo.getUsername());
        user.setMobile(wxUserRegisterVo.getMobile());
        user.setWeixinOpenid(wxUserRegisterVo.getWxCode());
        user.setStatus((byte) 0);
        user.setAddTime(date);
        user.setAvatar("http://localhost:8080/img/7/b/0/b/e/a/a2bc986fb-30ce-452b-bbce-686ff755ca27-%E6%86%A8%E6%89%B9%E9%BE%9F.jpg");
        user.setDeleted(false);
        userMapper.insert(user);
        return user;
    }

    @Override
    public void setRegisterCodeInfo(UserRegisterCode userRegisterCode) {
        String mobile = userRegisterCode.getMobile();
        List<UserRegisterCode> infos = userRegisterCodeMapper.selectInfoByMobile(mobile);
        if(infos.size() == 0) {
            userRegisterCodeMapper.setRegisterCodeInfo(userRegisterCode);
        } else {
            userRegisterCodeMapper.updateRegisterCodeInfo(userRegisterCode);
        }
    }

    @Override
    public void sendMessage(Map<String, String> map) throws CodeMessageException {
        Date date = new Date();
        Calendar rightNow = Calendar.getInstance();
        String mobile = map.get("mobile");
        // 随机生成一个验证码
        int i = (int)(Math.random() * 1000000);
        String  code = String.valueOf(i);
        List<UserRegisterCode> infos = userRegisterCodeMapper.selectInfoByMobile(mobile);
        if(infos.size() != 0) {
            UserRegisterCode info = infos.get(0);
            if(info.getNonrepeatTime().after(date)) {
                throw new CodeMessageException("验证码已发送 请勿频繁尝试");
            }
        }
        aliyunComponent.sendMsg(mobile, code);
        UserRegisterCode userRegisterCode = new UserRegisterCode();
        rightNow.setTime(date);
        rightNow.add(Calendar.MINUTE,1);
        Date date1 = rightNow.getTime();
        rightNow.add(Calendar.MINUTE,4);
        Date date2 = rightNow.getTime();
        userRegisterCode.setMobile(mobile);
        userRegisterCode.setCode(code);
        userRegisterCode.setNonrepeatTime(date1);
        userRegisterCode.setExpiredTime(date2);
        setRegisterCodeInfo(userRegisterCode);
    }

    @Override
    public void submitFeedback(Feedback feedback) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        User user = userMapper.selectByPrimaryKey(userId);
        feedback.setUserId(userId);
        feedback.setUsername(user.getUsername());
        feedback.setAddTime(new Date());
        feedback.setUpdateTime(new Date());
        feedback.setStatus(0);
        feedback.setDeleted(false);
        feedbackMapper.insert(feedback);
    }
}
