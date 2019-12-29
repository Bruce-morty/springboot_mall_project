package top.philxin.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import top.philxin.mapper.AdminMapper;
import top.philxin.mapper.PermissionMapper;
import top.philxin.model.Admin;
import top.philxin.model.AdminExample;
import top.philxin.model.PermissionExample;

import java.util.List;

/**
 * @ClassName: CustomRealm
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/29 0029 20:28
 * @version: v1.0
 */
@Configuration
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    PermissionMapper permissionMapper;

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        Admin admin = adminMapper.selectAdminByUsername(username);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, admin.getPassword(), this.getName());
        return authenticationInfo;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        String roleIds = adminMapper.selectRoleIdsByUsername(username);
        roleIds = "(" + roleIds.substring(1,roleIds.length() - 1) + ")";
        List<String> permissions = permissionMapper.selectPermissionsInRoleIds(roleIds);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);

        return authorizationInfo;
    }
}
