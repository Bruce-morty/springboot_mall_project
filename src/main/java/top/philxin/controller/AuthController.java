package top.philxin.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.requestModel.LoginVo;
import top.philxin.model.responseModel.AdminInfoVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;

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

    @RequestMapping("login")
    public BaseRespVo adminLogin(@RequestBody LoginVo loginVo) {
        return BaseRespVo.success("455d9fc7-ee3b-45bf-8968-2baa88bef5e8");
    }

    @RequestMapping("info")
    public BaseRespVo adminInfo() {
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
}
