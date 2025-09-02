package com.zhora.admin.manager;

import com.zhora.admin.shiro.web.session.SpringSessionValidationScheduler;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 确保应用退出时能关闭后台线程
 *
 * @author cj
 */
@Component
@Slf4j
public class ShutdownManager {

    @Autowired(required = false)
    private SpringSessionValidationScheduler springSessionValidationScheduler;

    @Autowired(required = false)
    private EhCacheManager ehCacheManager;

    @PreDestroy
    public void destroy() {
        shutdownSpringSessionValidationScheduler();
        shutdownAsyncManager();
        if (ehCacheManager != null) {
            // 内部会自动关闭 EhCacheManager
            ehCacheManager.destroy();
        }
    }

    /**
     * 停止Seesion会话检查
     */
    private void shutdownSpringSessionValidationScheduler() {
        if (springSessionValidationScheduler != null && springSessionValidationScheduler.isEnabled()) {
            try {
                log.info("====关闭会话验证任务====");
                springSessionValidationScheduler.disableSessionValidation();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 停止异步执行任务
     */
    private void shutdownAsyncManager() {
        try {
            log.info("====关闭后台任务任务线程池====");
            AsyncManager.me().shutdown();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
