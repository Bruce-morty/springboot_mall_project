package top.philxin.service.wx;

import top.philxin.exception.CodeExpiredException;
import top.philxin.exception.CodeMessageException;
import top.philxin.exception.UnPairedCodeException;
import top.philxin.exception.UsernameExistException;
import top.philxin.model.Feedback;
import top.philxin.model.User;
import top.philxin.model.UserRegisterCode;
import top.philxin.model.requestModel.WxUserRegisterVo;
import top.philxin.model.responseModel.WxUserModel.WxUserIndexOrderStatusInfoVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName: WxUserService
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/30 0030 20:21
 * @version: v1.0
 */
public interface WxUserService {
    User getUserInfoByName(String username);

    WxUserIndexOrderStatusInfoVo getUserIndexInfo(int userId);

    User userRegister(WxUserRegisterVo wxUserRegisterVo) throws UnPairedCodeException, UsernameExistException, CodeExpiredException;

    void setRegisterCodeInfo(UserRegisterCode userRegisterCode);

    void sendMessage(Map<String, String> map) throws CodeMessageException;

    void submitFeedback(Feedback feedback);
}
