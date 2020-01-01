package top.philxin.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import top.philxin.shiro.MallToken;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @ClassName: CustomAuthenticatior
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/30 0030 18:54
 * @version: v1.0
 */
public class CustomAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        this.assertRealmsConfigured();
        Collection<Realm> originRealms = this.getRealms();
        //对realms来进行分发
        MallToken token = (MallToken) authenticationToken;
        String type = token.getType();
        ArrayList<Realm> realms = new ArrayList<>();
        for (Realm originRealm : originRealms) {
            if (originRealm.getName().toLowerCase().contains(type)){
                realms.add(originRealm);
            }
        }

        return realms.size() == 1 ? this.doSingleRealmAuthentication((Realm)realms.iterator().next(), authenticationToken) :
                this.doMultiRealmAuthentication(realms, authenticationToken);
    }
}
