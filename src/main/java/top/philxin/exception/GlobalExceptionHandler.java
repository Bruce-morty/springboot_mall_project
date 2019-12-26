package top.philxin.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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
}
