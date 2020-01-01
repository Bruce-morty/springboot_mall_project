package top.philxin.exception;

/**
 * @ClassName: UnPairedCodeException
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/31 0031 22:56
 * @version: v1.0
 */
public class UnPairedCodeException extends Exception {
    String message;

    public UnPairedCodeException(String message) {
        this.message = message;
    }
}
