package top.philxin.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.annotation.LogRecordAnno;
import top.philxin.model.requestModel.LoginVo;
import top.philxin.model.responseModel.AdminInfoVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AuthController
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/25 0025 22:49
 * @version: v1.0
 */
@RestController
@RequestMapping("admin/auth")
public class AuthController {

    @RequestMapping("unAuthc")
    public BaseRespVo unAuthc(){
        return BaseRespVo.error(502,"请登录后访问");
    }

    @LogRecordAnno(operateAction = "登录")
    @RequestMapping("login")
    public BaseRespVo adminLogin(@RequestBody LoginVo loginVo) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(loginVo.getUsername(),loginVo.getPassword()));
        } catch (AuthenticationException e) {
            return BaseRespVo.error(502,"请登录后访问");
            //e.printStackTrace();
        }
        Serializable id = subject.getSession().getId();
        return BaseRespVo.success(id);
    }

    @RequestMapping("info")
    public BaseRespVo adminInfo() {
        Subject subject = SecurityUtils.getSubject();
        AdminInfoVo adminInfoVo = new AdminInfoVo();
        adminInfoVo.setName("admin123");
        adminInfoVo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        List<String> permList = new ArrayList<>();
        permList.add("*");
        List<String> roleList = new ArrayList<>();
        roleList.add("超级管理员");
        adminInfoVo.setPerms(permList);
        adminInfoVo.setRoles(roleList);
        return BaseRespVo.success(adminInfoVo);
    }

    @RequestMapping("logout")
    public BaseRespVo adminLogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseRespVo.success();
    }
}
