package com.zhora.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 获取i18n资源文件
 *
 * @author zhehen.lu
 */
public class MessageUtils {
    private static MessageSource messageSource;
    static {
        messageSource = (MessageSource)ApplicationContextUtil.getBean("messageSource");
    }

    public static String getMessage(int code){
        return getMessage(code, new String[0]);
    }

    public static String getMessage(int code, String... params){
        return messageSource.getMessage(code+"", params, LocaleContextHolder.getLocale());
    }
}
