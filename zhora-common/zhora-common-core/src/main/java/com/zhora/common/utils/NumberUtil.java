package com.zhora.common.utils;

/**
 * 数字工具
 *
 * @author zhehen.lu
 * @date 2024/6/25 10:29
 */
public class NumberUtil {
    /**
     * 是否大于0
     * @param num
     * @return {@link boolean}
     * @date 2024/6/25 10:29
     * @author zhehen.lu
     */
    public static boolean isGtZero(Long num) {
        return num != null && num.longValue() > 0L;
    }

    /**
     * 是否大于0
     * @param num
     * @return {@link boolean}
     * @date 2024/6/25 10:29
     * @author zhehen.lu
     */
    public static boolean isGtZero(Integer num) {
        return num != null && num.intValue() > 0;
    }

    /**
     * 是否大于等于0
     * @param num
     * @return {@link boolean}
     * @date 2024/6/25 10:29
     * @author zhehen.lu
     */
    public static boolean isGtzZero(Integer num) {
        return num != null && num.intValue() >= 0;
    }

    /**
     * 是否大于等于0
     * @param num
     * @return {@link boolean}
     * @date 2024/6/25 10:29
     * @author zhehen.lu
     */
    public static boolean isGtzZero(Long num) {
        return num != null && num.longValue() >= 0L;
    }

    /**
     * 在左边填充0
     * @param num
     * @param bit
     * @return {@link String}
     * @date 2024/6/25 10:29
     * @author zhehen.lu
     */
    public static String paddingLeftZero(int num, int bit) {
        return padding(num, bit, 0, "0");
    }

    /**
     * 填充
     * @param num
     * @param bit
     * @param paddingDirt
     * @param paddingStr
     * @return {@link String}
     * @date 2024/6/25 10:29
     * @author zhehen.lu
     */
    public static String padding(int num, int bit, int paddingDirt, String paddingStr) {
        String numStr = String.valueOf(num);
        if (numStr.length() >= bit) {
            return numStr;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i=numStr.length(); i<bit; i++) {
            stringBuilder.append(paddingStr);
        }
        if (paddingDirt == 0) {
            stringBuilder.append(numStr);
        } else {
            stringBuilder.insert(0, numStr);
        }

        return stringBuilder.toString();
    }
}
