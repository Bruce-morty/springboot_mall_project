package top.philxin.exception;

/**
 * @ClassName: CodeMessageException
 * @Description: TODO
 * @author: Administrator
 * @date: 2020/1/1 0001 17:49
 * @version: v1.0
 */
public class CodeMessageException extends Exception {
    String message;

    public CodeMessageException(String message) {
        this.message = message;
    }
}
