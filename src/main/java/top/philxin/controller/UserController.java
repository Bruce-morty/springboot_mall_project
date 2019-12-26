package top.philxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.User;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.UserService;

import java.util.List;

/**
 * @ClassName: UserController
 * @Description: 用户模块管理
 * @author: Administrator
 * @date: 2019/12/26 0026 15:18
 * @version: v1.0
 */
@RestController
@RequestMapping("admin")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("user/list")
    public BaseRespVo getAllUser(PageHelperVo pageHelperVo,String username,String mobile) {
        BaseDataVo baseDataVo = userService.queryUsers(pageHelperVo, username, mobile);
        return BaseRespVo.success(baseDataVo);
    }
}
