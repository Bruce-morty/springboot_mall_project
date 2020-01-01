package top.philxin.exception;

/**
 * @ClassName: UsernameExistException
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/31 0031 23:00
 * @version: v1.0
 */
public class UsernameExistException extends Exception {
    String message;

    public UsernameExistException(String message) {
        this.message = message;
    }
}
