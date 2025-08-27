package com.zhora.common.utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 集合处理
 *
 * @author zhehen.lu
 * @date 2025/8/27 16:40
 */
public class ListUtils {

    /**
     * 方法1：使用 Stream API 根据字段获取并集
     *
     * @param list1        第一个列表
     * @param list2        第二个列表
     * @param keyExtractor 字段提取函数
     * @return 并集列表
     */
    public static <T> List<T> getUnionByField(
            List<T> list1,
            List<T> list2,
            Function<? super T, ?> keyExtractor) {
        return Stream.concat(list1.stream(), list2.stream())
                .filter(distinctByKey(keyExtractor))
                .collect(Collectors.toList());
    }

    /**
     * 辅助方法：根据字段去重
     */
    private static <T> java.util.function.Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new HashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 方法2：使用循环和 Set 根据字段获取并集
     *
     * @param list1        第一个列表
     * @param list2        第二个列表
     * @param keyExtractor 字段提取函数
     * @return 并集列表
     */
    public static <T> List<T> getUnionByFieldWithLoop(
            List<T> list1,
            List<T> list2,
            Function<? super T, ?> keyExtractor) {

        Set<Object> seenKeys = new HashSet<>();
        List<T> result = new ArrayList<>();

        // 处理第一个列表
        for (T item : list1) {
            Object key = keyExtractor.apply(item);
            if (seenKeys.add(key)) { // 如果key不存在则添加
                result.add(item);
            }
        }

        // 处理第二个列表
        for (T item : list2) {
            Object key = keyExtractor.apply(item);
            if (seenKeys.add(key)) { // 如果key不存在则添加
                result.add(item);
            }
        }

        return result;
    }

    /**
     * 方法1：使用Stream API根据字段获取交集
     * @param list1 第一个列表
     * @param list2 第二个列表
     * @param keyExtractor 字段提取函数
     * @return 交集列表（基于list1）
     */
    public static <T> List<T> getIntersectionByFieldStream(
            List<T> list1,
            List<T> list2,
            Function<? super T, ?> keyExtractor) {

        // 将第二个列表的字段值存入Set
        Set<Object> set2 = list2.stream()
                .map(keyExtractor)
                .collect(Collectors.toSet());

        // 过滤第一个列表，保留字段值在set2中的元素
        return list1.stream()
                .filter(item -> set2.contains(keyExtractor.apply(item)))
                .collect(Collectors.toList());
    }

    /**
     * 方法2：使用Set和循环获取交集
     * @param list1 第一个列表
     * @param list2 第二个列表
     * @param keyExtractor 字段提取函数
     * @return 交集列表
     */
    public static <T> List<T> getIntersectionByFieldSet(
            List<T> list1,
            List<T> list2,
            Function<? super T, ?> keyExtractor) {

        // 将第二个列表的字段值存入Set
        Set<Object> set2 = new HashSet<>();
        for (T item : list2) {
            set2.add(keyExtractor.apply(item));
        }

        // 遍历第一个列表，保留字段值在set2中的元素
        List<T> result = new ArrayList<>();
        for (T item : list1) {
            if (set2.contains(keyExtractor.apply(item))) {
                result.add(item);
            }
        }

        return result;
    }

    /**
     * 方法3：获取所有匹配项（保留两个列表中的所有匹配元素）
     * @param list1 第一个列表
     * @param list2 第二个列表
     * @param keyExtractor 字段提取函数
     * @return 所有匹配元素的列表
     */
    public static <T> List<T> getAllMatchesByField(
            List<T> list1,
            List<T> list2,
            Function<? super T, ?> keyExtractor) {

        // 创建字段到元素的映射
        Map<Object, T> map1 = list1.stream()
                .collect(Collectors.toMap(
                        keyExtractor,
                        item -> item,
                        (existing, replacement) -> existing)); // 重复时保留第一个

        // 收集匹配项
        List<T> result = new ArrayList<>();
        for (T item : list2) {
            Object key = keyExtractor.apply(item);
            if (map1.containsKey(key)) {
                // 添加第一个列表中的元素
                result.add(map1.get(key));
                // 添加第二个列表中的元素
                result.add(item);
            }
        }

        return result;
    }

    /**
     * 方法4：根据多个字段获取交集
     * @param list1 第一个列表
     * @param list2 第二个列表
     * @param keyExtractor 多字段提取函数
     * @return 交集列表
     */
    public static <T> List<T> getIntersectionByMultipleFields(
            List<T> list1,
            List<T> list2,
            Function<T, List<Object>> keyExtractor) {

        // 将第二个列表的多字段组合存入Set
        Set<List<Object>> set2 = list2.stream()
                .map(keyExtractor)
                .collect(Collectors.toSet());

        // 过滤第一个列表
        return list1.stream()
                .filter(item -> set2.contains(keyExtractor.apply(item)))
                .collect(Collectors.toList());
    }
}
