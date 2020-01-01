package top.philxin.config;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;


/**
 * @ClassName: CustomSessionManager
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/29 0029 20:51
 * @version: v1.0
 */
public class CustomSessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest srequest, ServletResponse response) {
        HttpServletRequest request = (HttpServletRequest) srequest;
        String sessionId = request.getHeader("X-cskaoyan-mall-admin-token");
        if (sessionId  != null && !"".equals(sessionId )){
            return sessionId ;
        }
        return super.getSessionId(request, response);
    }
}
