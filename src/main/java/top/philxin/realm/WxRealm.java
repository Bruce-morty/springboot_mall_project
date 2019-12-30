package top.philxin.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.philxin.mapper.UserMapper;
import top.philxin.model.User;
import top.philxin.shiro.MallToken;

/**
 * @ClassName: WxRealm
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/30 0030 19:12
 * @version: v1.0
 */
@Component
public class WxRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token =(UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = userMapper.selectPasswordByUsername(username);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,password,this.getName());
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
