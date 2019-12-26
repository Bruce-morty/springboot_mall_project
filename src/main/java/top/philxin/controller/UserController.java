package top.philxin.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.requestModel.PageHelperVo;
import top.philxin.model.responseModel.BaseRespVo;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 15:18
 * @version: v1.0
 */
@RestController
@RequestMapping("admin")
public class UserController {
    @RequestMapping("user/list")
    public BaseRespVo getAllUser(@RequestBody PageHelperVo pageHelperVo) {
        System.out.println(pageHelperVo);
        return BaseRespVo.success();
    }
}
