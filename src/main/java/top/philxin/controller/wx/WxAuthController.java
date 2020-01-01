package top.philxin.controller.wx;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.User;
import top.philxin.model.requestModel.LoginVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.model.responseModel.WxUserModel.WxUserIndexOrderStatusInfoVo;
import top.philxin.model.responseModel.WxUserModel.WxUserInfoVo;
import top.philxin.service.wx.WxUserService;
import top.philxin.shiro.MallToken;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: WxAuthController
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/30 0030 17:01
 * @version: v1.0
 */
@RestController
@RequestMapping("wx")
public class WxAuthController {

    @Autowired
    WxUserService wxUserService;

    /**
     * 用户登录
     * @param loginVo
     * @return
     */
    @RequestMapping("auth/login")
    public BaseRespVo userLogin(@RequestBody LoginVo loginVo) {
        MallToken mallToken = new MallToken(loginVo.getUsername(), loginVo.getPassword(), "wx");
        Subject subject = SecurityUtils.getSubject();
        subject.login(mallToken);

        // 获取sessionId
        Serializable id = subject.getSession().getId();
        // 获取用户昵称与头像
        User userInfo = wxUserService.getUserInfoByName(loginVo.getUsername());
        String nickname = userInfo.getNickname();
        String avatar = userInfo.getAvatar();
        WxUserInfoVo wxUserInfoVo = new WxUserInfoVo();
        wxUserInfoVo.setNickName(nickname);
        wxUserInfoVo.setAvatarUrl(avatar);

        Map<String,Object> map = new HashMap<>();
        Date date = new Date();
        date.setTime(date.getTime() + 30*60000);
        map.put("token",id);
        map.put("tokenExpire",date);
        map.put("userInfo",wxUserInfoVo);

        // 登录成功后将用户编号存入session
        subject.getSession().setAttribute("userId",userInfo.getId());

        return BaseRespVo.success(map);
    }

    /**
     * 用户登出
     * @return
     */
    @RequestMapping("auth/logout")
    public BaseRespVo userLogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseRespVo.success();
    }

    /**
     * 获取用户个人中心数据
     * @return
     */
    @RequestMapping("user/index")
    public BaseRespVo userIndex() {
        Subject subject = SecurityUtils.getSubject();
        int userId = (int) subject.getSession().getAttribute("userId");
        WxUserIndexOrderStatusInfoVo info = wxUserService.getUserIndexInfo(userId);
        Map<String,Object> map = new HashMap<>();
        map.put("order",info);
        return BaseRespVo.success(map);
    }


}
