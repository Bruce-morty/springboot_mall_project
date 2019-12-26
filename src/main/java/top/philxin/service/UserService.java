package top.philxin.service;

import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;


/**
 * @ClassName: UserService
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 16:34
 * @version: v1.0
 */
public interface UserService {
    BaseDataVo queryUsers(PageHelperVo pageHelperVo, String username, String mobile);
}
