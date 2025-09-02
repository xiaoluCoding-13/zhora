package com.zhora.admin.shiro.web.filter;

import com.zhora.admin.manager.AsyncManager;
import com.zhora.admin.manager.factory.AsyncFactory;
import com.zhora.admin.shiro.util.ShiroUtils;
import com.zhora.common.utils.ApplicationContextUtil;
import com.zhora.common.utils.MessageUtils;
import com.zhora.common.utils.StringUtils;
import com.zhora.constant.Constants;
import com.zhora.dto.system.SysUserDTO;
import com.zhora.service.system.ISysUserOnlineService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 退出过滤器
 *
 * @author ruoyi
 */
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {
    private static final Logger log = LoggerFactory.getLogger(LogoutFilter.class);

    /**
     * 退出后重定向的地址
     */
    private String loginUrl;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        try {
            Subject subject = getSubject(request, response);
            String redirectUrl = getRedirectUrl(request, response, subject);
            try {
                SysUserDTO user = ShiroUtils.getSysUser();
                if (StringUtils.isNotNull(user)) {
                    String loginName = user.getLoginName();
                    // 记录用户退出日志
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGOUT, MessageUtils.message("user.logout.success")));
                    // 清理缓存
                    ApplicationContextUtil.getBean(ISysUserOnlineService.class).removeUserCache(loginName, ShiroUtils.getSessionId());
                }
                // 退出登录
                subject.logout();
            } catch (SessionException ise) {
                log.error("logout fail.", ise);
            }
            issueRedirect(request, response, redirectUrl);
        } catch (Exception e) {
            log.error("Encountered session exception during logout.  This can generally safely be ignored.", e);
        }
        return false;
    }

    /**
     * 退出跳转URL
     */
    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        String url = getLoginUrl();
        if (StringUtils.isNotEmpty(url)) {
            return url;
        }
        return super.getRedirectUrl(request, response, subject);
    }
}
