package com.zhora.common.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanCopier;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * bean转换工具
 * @author zhehen.lu
 * @date 2024/6/24 17:03
 */
@Slf4j
public class ConvertUtil {
    public static <S, T> T convert(S source, Class<T> dest, Consumer<T> function) {
        if (source == null) {
            return null;
        }
        try {
            T result = dest.newInstance();
            final BeanCopier copier = BeanCopier.create(source.getClass(), dest, false);
            copier.copy(source, result, null);
            if (function != null) {
                function.accept(result);
            }
            return result;
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("convert error", e);
        }
        return null;
    }

    public static <S, T> T convert(S source, Class<T> dest) {
        return convert(source, dest, null);
    }

    public static <S, T> T convert(S source, T dest) {
        if (source == null || dest == null) {
            return null;
        }
        T result = dest;
        final BeanCopier copier = BeanCopier.create(source.getClass(), dest.getClass(), false);
        copier.copy(source, result, null);
        return result;
    }

    public static <S, T> List<T> convertList(List<S> source, Class<T> dest) {
        return convertList(source, dest, null);
    }

    public static <S, T> List<T> convertList(List<S> source, Class<T> dest, ConvertCallback<S, T> callback) {
        if (source == null) {
            return null;
        }
        return source.stream().map(s -> {
            T result = null;
            try {
                result = dest.newInstance();
                convert(s, result);
                if (callback != null) {
                    callback.callback(s, result);
                }
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("convert error", e);
            }
            return result;
        }).collect(Collectors.toList());
    }

    public interface ConvertCallback<S, D> {
        void callback(S source, D dest);
    }
}
