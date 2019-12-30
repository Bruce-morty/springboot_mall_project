package top.philxin.service.wx;

import top.philxin.model.User;
import top.philxin.model.responseModel.WxUserModel.WxUserInfoVo;

/**
 * @ClassName: WxUserService
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/30 0030 20:21
 * @version: v1.0
 */
public interface WxUserService {
    User getUserInfoByName(String username);
}
