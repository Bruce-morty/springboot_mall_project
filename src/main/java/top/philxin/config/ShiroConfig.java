package top.philxin.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.philxin.realm.AdminRealm;
import top.philxin.realm.WxRealm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName: ShiroConfig
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/29 0029 20:16
 * @version: v1.0
 */
@Configuration
public class ShiroConfig {

    // shiro过滤器生成工厂
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBea(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //认证失败后重定向的url
        shiroFilterFactoryBean.setLoginUrl("/auth/unAuthc");
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        // 后台页面访问
        // 放开静态资源访问路径

        filterMap.put("/img/**","anon");
        // 放开登录的url过滤

        filterMap.put("/admin/auth/login","anon");
        // 放开登出的url过滤
        filterMap.put("/admin/auth/logout","anon");
        filterMap.put("/admin/auth/info","anon");
        // 放开对druid监控的过滤
        filterMap.put("/druid/**","anon");

        // 前台页面访问(目前全放开)
        filterMap.put("/wx/**","anon");

        // 需要认证才能访问的路径
        filterMap.put("/**","authc");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //shiro核心组件 SecurityManager
    @Bean
    public DefaultWebSecurityManager securityManager(AdminRealm adminRealm,WxRealm wxRealm,
                                                     DefaultWebSessionManager webSessionManager,
                                                     CustomAuthenticator authenticator){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        List<Realm> realmList = new ArrayList<>();
        realmList.add(adminRealm);
        realmList.add(wxRealm);
        defaultWebSecurityManager.setRealms(realmList);
//      defaultWebSecurityManager.setSessionManager(webSessionManager());
        defaultWebSecurityManager.setSessionManager(webSessionManager);
        defaultWebSecurityManager.setAuthenticator(authenticator);
        return defaultWebSecurityManager;
    }

    /*
     * 声明式鉴权 注解需要的组件
     * */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultWebSessionManager webSessionManager(){
        CustomSessionManager customSessionManager = new CustomSessionManager();
        return customSessionManager;
    }

    @Bean
    public CustomAuthenticator authenticator(AdminRealm adminRealm, WxRealm wxRealm){
        CustomAuthenticator customAuthenticator = new CustomAuthenticator();
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        realms.add(wxRealm);
        customAuthenticator.setRealms(realms);
        return customAuthenticator;
    }

}
