package com.ykyy.ec.common.util;

import com.ykyy.ec.common.errcode.ErrorCode;
import com.ykyy.ec.common.exception.BaseException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @Classname ValidateUtil
 * @Description 校验判断工具
 * @Date 2022/7/11 9:59
 * @Created by sherlock
 */
public class ValidateUtil {
    /**
     * 判断是否为true
     * @param value
     * @param code
     */
    public static void isTrue(Boolean value, ErrorCode code) {
        if (!Boolean.TRUE.equals(value)) {
            throw new BaseException(code);
        }
    }

    /**
     * 判断是否为true
     * @param value
     * @param code
     * @param msg
     */
    public static void isTrue(Boolean value, ErrorCode code, String msg) {
        if (!Boolean.TRUE.equals(value)) {
            throw new BaseException(code, msg);
        }
    }

    /**
     * 判断是否为false  为false则正常 为true则抛异常
     * @param value
     * @param code
     */
    public static void isFalse(Boolean value, ErrorCode code) {
        if (!Boolean.FALSE.equals(value)) {
            throw new BaseException(code);
        }
    }

    /**
     * 判断是否为false  为false则正常 为true则抛异常
     * @param value
     * @param code
     * @param msg
     */
    public static void isFalse(Boolean value, ErrorCode code, String msg) {
        if (!Boolean.FALSE.equals(value)) {
            throw new BaseException(code, msg);
        }
    }

    /**
     * 判断字符串为空 不为空抛出错误
     * @param value
     * @param code
     */
    public static void empty(String value, ErrorCode code) {
        if (value != null && (!value.isEmpty())) {
            throw new BaseException(code);
        }
    }

    /**
     *  判断集合为空， 不为空则抛出错误
     * @param value
     * @param code
     */
    public static void empty(Collection value, ErrorCode code) {
        if (value != null && (!CollectionUtils.isEmpty(value))) {
            throw new BaseException(code);
        }
    }

    /**
     *  判断集合为空， 不为空则抛出错误
     * @param value
     * @param code
     * @param msg
     */
    public static void empty(Collection value, ErrorCode code, String msg) {
        if (value != null && (!CollectionUtils.isEmpty(value))) {
            throw new BaseException(code, msg);
        }
    }

    /**
     * 判断字符串不为空 ， 为空抛出错误
     * @param value
     * @param code
     */
    public static void notEmpty(String value, ErrorCode code) {
        if (value == null || value.isEmpty()) {
            throw new BaseException(code);
        }
    }

    /**
     * 判断集合不为空 为空抛出错误
     * @param value
     * @param code
     */
    public static void notEmpty(Collection value, ErrorCode code) {
        if (CollectionUtils.isEmpty(value)) {
            throw new BaseException(code);
        }
    }

    /**
     * 判断集合不为空 为空抛出错误
     * @param value
     * @param code
     */
    public static void notEmpty(Map value, ErrorCode code) {
        if (CollectionUtils.isEmpty(value)) {
            throw new BaseException(code);
        }
    }

    /**
     * 判断集合不为空 为空抛出错误
     * @param value
     * @param code
     * @param msg
     */
    public static void notEmpty(Collection value, ErrorCode code, String msg) {
        if (CollectionUtils.isEmpty(value)) {
            throw new BaseException(code, msg);
        }
    }

    /**
     * 判断字符串不为空 为空抛出错误
     * @param value
     * @param code
     * @param msg
     */
    public static void notEmpty(String value, ErrorCode code, String msg) {
        if (StringUtils.isEmpty(value)) {
            throw new BaseException(code, msg);
        }
    }

    /**
     * 判断对象不为null， 为null报错
     * @param value
     * @param code
     */
    public static void notNull(Object value, ErrorCode code) {
        if (value == null) {
            throw new BaseException(code);
        }
    }

    /**
     * 判断对象不为null， 为null报错
     * @param value
     * @param code
     * @param msg
     */
    public static void notNull(Object value, ErrorCode code, String msg) {
        if (value == null) {
            throw new BaseException(code, msg);
        }
    }

    /**
     * 判断map不为null， 为null报错
     * @param value
     * @param code
     */
    public static void notNull(Map value, ErrorCode code) {
        if (value == null) {
            throw new BaseException(code);
        }
    }

    /**
     * 判断map不为null， 为null报错
     * @param value
     * @param code
     * @param msg
     */
    public static void notNull(Map value, ErrorCode code, String msg) {
        if (value == null) {
            throw new BaseException(code, msg);
        }
    }

    /**
     * 判断必须为null
     * @param value
     * @param code
     * @date 2023/3/8 14:06
     * @author sherlock
     */
    public static void mustNull(Object value, ErrorCode code) {
        if (value != null) {
            throw new BaseException(code);
        }
    }

    /**
     * 判断long值必须大于0
     * @param value
     * @param code
     */
    public static void mustGtZero(Long value, ErrorCode code) {
        if (value == null || value.longValue() <= 0L) {
            throw new BaseException(code);
        }
    }

    /**
     * 判断long值必须大于0
     * @param value
     * @param code
     * @param msg
     */
    public static void mustGtZero(Long value, ErrorCode code, String msg) {
        if (value == null || value.longValue() <= 0L) {
            throw new BaseException(code, msg);
        }
    }

    /**
     * 判断int值必须大于0
     * @param value
     * @param code
     */
    public static void mustGtZero(Integer value, ErrorCode code) {
        if (value == null || value.intValue() <= 0L) {
            throw new BaseException(code);
        }
    }

    /**
     * 判断int值必须大于0
     * @param value
     * @param code
     * @param msg
     * @date 2023/3/3 16:56
     * @author sherlock
     */
    public static void mustGtZero(Integer value, ErrorCode code, String msg) {
        if (value == null || value.intValue() <= 0L) {
            throw new BaseException(code);
        }
    }

    /**
     * 判断long值必须大于等于0
     * @param value
     * @param code
     * @date 2023/3/16 10:01
     * @author sherlock
     */
    public static void mustGteZero(Long value, ErrorCode code) {
        if (value == null || value.longValue() < 0L) {
            throw new BaseException(code);
        }
    }

    /**
     * 判断int值必须大于等于0
     * @param value
     * @param code
     * @date 2023/3/16 10:01
     * @author sherlock
     */
    public static void mustGteZero(Integer value, ErrorCode code) {
        if (value == null || value.intValue() < 0) {
            throw new BaseException(code);
        }
    }

    /**
     * 判断数字是否在这个之间
     * @param number
     * @param min
     * @param max
     * @param code
     * @date 2023/3/6 13:12
     * @author sherlock
     */
    public static void numberRange(long number, long min, long max, ErrorCode code) {
        if (number < min || number> max) {
            throw new BaseException(code);
        }
    }

    /**
     * 判断数字是否在这个之间
     * @param number
     * @param min
     * @param max
     * @param code
     * @param msg
     *
     * @date 2023/8/24 16:25
     * @author sherlock
     */
    public static void numberRange(long number, long min, long max, ErrorCode code, String msg) {
        if (number < min || number> max) {
            throw new BaseException(code, msg);
        }
    }

    /**
     * 判断字符串长度必须在一个范围内
     * @param str
     * @param minLen
     * @param maxLen
     * @param code
     */
    public static void strLengthRange(String str, Integer minLen, Integer maxLen, ErrorCode code) {
        if (StringUtils.isEmpty(str)) {
            throw new BaseException(code);
        }

        if (str.length() < minLen || str.length() > maxLen) {
            throw new BaseException(code);
        }
    }

    /**
     * 判断字符串长度最大值不能超过某个值
     * @param str
     * @param maxLen
     * @param code
     * @param msg
     */
    public static void strLengthMax(String str, Integer maxLen, ErrorCode code, String msg) {
        if (!StringUtils.isEmpty(str) && str.length() > maxLen) {
            throw new BaseException(code, msg);
        }
    }

    /**
     * 判断字符串长度最大值不能超过某个值
     * @param str
     * @param maxLen
     * @param code
     * @Date 2022/10/25 11:24
     * @author sherlock
     */
    public static void strLengthMax(String str, Integer maxLen, ErrorCode code) {
        if (!StringUtils.isEmpty(str) && str.length() > maxLen) {
            throw new BaseException(code);
        }
    }

}
