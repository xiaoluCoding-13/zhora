package com.ykyy.ec.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 获取应用上下文
 *
 * @author by sherlock
 * @Date 2022/11/1 19:00
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取应用上下文
     * @return {@link ApplicationContext}
     * @date  2022/8/26 15:27
     * @author zsx
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     * @param name bean名称
     * @return {@link Object}
     * @date  2022/8/26 15:28
     * @author zsx
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过bean类型获取Bean.
     * @param clazz bean类型
     * @return {@link T}
     * @date  2022/8/26 15:28
     * @author zsx
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过bean名称,以及bean类型返回指定的Bean
     * @param name bean名称
     * @param clazz bean类型
     * @return {@link T}
     * @date  2022/8/26 15:25
     * @author zsx
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }


    /**
     * 根据bean类型获取bean列表
     * @param clazz bean类型
     * @return {@link List <T>}
     * @date  2022/8/26 15:24
     * @author zsx
     */
    public static <T> List<T> getBeans(Class<T> clazz) {
        Map<String, T> beansOfMap = getApplicationContext().getBeansOfType(clazz);
        if (! CollectionUtils.isEmpty(beansOfMap)) {
            return new ArrayList<>(beansOfMap.values());
        }
        return new ArrayList<>();
    }

    /**
     * 获取当前应用名
     * @return {@link String}
     * @date  2022/8/26 15:24
     * @author zsx
     */
    public static String getAppName(){
        return applicationContext.getApplicationName();
    }
}
