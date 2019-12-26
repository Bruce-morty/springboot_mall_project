package top.philxin.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.philxin.model.responseModel.BaseRespVo;

@ControllerAdvice
@ResponseBody
public class GloabExceptionHandler {

    @ExceptionHandler
    public BaseRespVo unknownException(Exception e) {
        return BaseRespVo.error(-99, "未知错误，请联系系统管理员");
    }
}
