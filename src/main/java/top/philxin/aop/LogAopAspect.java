package top.philxin.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.philxin.annotation.LogRecordAnno;
import top.philxin.model.Log;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.LogRecordService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @ClassName: LogAopAspect
 * @Description: Log操作
 * @author: Administrator
 * @date: 2019/12/29 0029 13:42
 * @version: v1.0
 */
@Component
@Aspect
public class LogAopAspect {

    @Autowired
    LogRecordService logRecordService;

    @Pointcut("@annotation(top.philxin.annotation.LogRecordAnno)")
    public void mypointcut() {}

    @Around("mypointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        // 获取方法签名
        MethodSignature methodSignature =(MethodSignature) joinPoint.getSignature();
        // 获取相关方法
        Method method = methodSignature.getMethod();
        // 获取方法上的注解
        LogRecordAnno annotation = method.getAnnotation(LogRecordAnno.class);
        // 获取操作的描述值
        String operateType = annotation.operateAction();
        // 获取操作的备注值
        String operateComment = annotation.operateComment();
        // 获取操作类型
        int level = annotation.operateType();

        // 创建一个日志对象
        Log log = new Log();
        // 设置操作描述值
        log.setAction(operateType);
        // 获取当前请求，通过当前请求获取请求的IpAddress
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.setIp(request.getRemoteAddr());
        // 设置操作者
        log.setAdmin("admin123"); // shiro写完后设置
        // 设置操作时间
        Date date = new Date();
        log.setAddTime(date);
        log.setUpdateTime(date);
        // 设置操作action
        log.setAction(operateType);
        // 设置操作备注
        if(!operateComment.isEmpty()) {
            log.setComment(operateComment);
        }
        // 设置操作级别
        log.setType(level);
        log.setDeleted(false);
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            BaseRespVo baseRespVo = (BaseRespVo) proceed;
            int errno = baseRespVo.getErrno();
            String errmsg = baseRespVo.getErrmsg();
            // 判断执行结果的返回码，判断是否成功
            log.setStatus(errno == 0 ? true : false);
            log.setResult(errmsg);
            logRecordService.addLog(log);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }
}
