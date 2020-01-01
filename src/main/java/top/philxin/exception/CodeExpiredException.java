package top.philxin.exception;

/**
 * @ClassName: CodeExpiredException
 * @Description: TODO
 * @author: Administrator
 * @date: 2020/1/1 0001 20:21
 * @version: v1.0
 */
public class CodeExpiredException extends Exception{
    String message;

    public CodeExpiredException(String message) {
        this.message = message;
    }
}
