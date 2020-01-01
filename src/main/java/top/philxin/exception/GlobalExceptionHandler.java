package top.philxin.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.philxin.model.User;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public BaseRespVo unknownException(Exception e) {
        logger.error("未知错误",e);
        return BaseRespVo.error(-99, "未知错误，请联系系统管理员");
    }

    @ExceptionHandler
    public BaseRespVo classCastException(ClassCastException e) {
        logger.error("参数类型不匹配",e);
        return BaseRespVo.error(-98,"参数类型不匹配");
    }

    @ExceptionHandler
    public BaseRespVo unPairedCodeException(UnPairedCodeException e) {
        logger.error("验证码错误",e);
        return BaseRespVo.error(-97,"验证码错误");
    }

    @ExceptionHandler
    public BaseRespVo usernameExistException(UsernameExistException e) {
        logger.error("用户名已存在",e);
        return BaseRespVo.error(-96,"用户名已存在");
    }

    @ExceptionHandler
    public BaseRespVo codeMessageException(CodeMessageException e) {
        logger.error("验证码发送频繁",e);
        return BaseRespVo.error(-95,"验证码发送频繁");
    }

    @ExceptionHandler
    public BaseRespVo codeExpiredException(CodeExpiredException e) {
        logger.error("验证码已过期",e);
        return BaseRespVo.error(-95,"验证码已过期");
    }

    @ExceptionHandler
    public BaseRespVo HttpMessageNotReadableException(HttpMessageNotReadableException e) {

        return BaseRespVo.error(402,"参数值不对");
    }
}
