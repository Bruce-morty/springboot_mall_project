package top.philxin.annotation;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogRecordAnno {
    String operateAction(); // 记录
    String operateComment() default ""; // 日志备注（默认为空）
    int operateType() default 1; // 操作级别（默认为1）
}
