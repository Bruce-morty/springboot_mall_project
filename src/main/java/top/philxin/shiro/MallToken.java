package top.philxin.shiro;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @ClassName: MallToken
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/30 0030 17:37
 * @version: v1.0
 */
@Data
public class MallToken extends UsernamePasswordToken {

    String type;

    public MallToken(String username, String password, String type) {
        super(username,password);
        this.type = type;
    }
}
