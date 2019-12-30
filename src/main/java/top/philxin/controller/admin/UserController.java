package top.philxin.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.admin.UserService;

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

    @RequestMapping("address/list")
    public BaseRespVo getAllAddress(PageHelperVo pageHelperVo, String userId, String name) {
        BaseDataVo baseDataVo = userService.queryAddresss(pageHelperVo, userId, name);
        return BaseRespVo.success(baseDataVo);
    }

    @RequestMapping("collect/list")
    public BaseRespVo getAllCollects(PageHelperVo pageHelperVo, String userId, String valueId) {
        BaseDataVo baseDataVo = userService.queryCollects(pageHelperVo, userId, valueId);
        return BaseRespVo.success(baseDataVo);
    }

    @RequestMapping("feedback/list")
    public BaseRespVo getAllFeedback(PageHelperVo pageHelperVo, String username, String id) {
        BaseDataVo baseDataVo = userService.queryFeedbacks(pageHelperVo, username, id);
        return BaseRespVo.success(baseDataVo);
    }

    @RequestMapping("footprint/list")
    public BaseRespVo getAllFootprints(PageHelperVo pageHelperVo, String userId, String goodsId) {
        BaseDataVo baseDataVo = userService.queryFootprints(pageHelperVo, userId, goodsId);
        return BaseRespVo.success(baseDataVo);
    }

    @RequestMapping("history/list")
    public BaseRespVo getAllSearchHistory(PageHelperVo pageHelperVo, String userId, String keyword) {
        BaseDataVo baseDataVo = userService.querySearchHistory(pageHelperVo, userId, keyword);
        return BaseRespVo.success(baseDataVo);
    }
}
