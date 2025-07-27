package com.zhora.common.dto;

import com.zhora.common.constant.CommonConstant;
import com.zhora.common.errcode.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

/**
 * api接口返回模型
 * @author zhehen.lu
 * @date 2025-07-27 15:10
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "rest请求的返回模型，所有rest正常都返回该类的对象")
public class ResponseDTO<T> {
    @Schema(description = "处理结果code",  requiredMode = Schema.RequiredMode.REQUIRED)
    private int code;
    @Schema(description = "处理结果描述信息")
    private String message;
    @Schema(description = "处理结果数据信息")
    private T data;
    @Schema(description = "traceId")
    private String traceId;

    /**
     * 返回
     * @param code
     * @param message
     * @return {@link ResponseDTO<T>}
     * @date 2024/6/25 09:19
     * @author zhehen.lu
     */
    public static <T> ResponseDTO<T> response(int code, String message) {
        return response(code, message, null);
    }

    /**
     * 返回
     * @param code
     * @param message
     * @param data
     * @return {@link ResponseDTO<T>}
     * @date 2024/6/25 09:19
     * @author zhehen.lu
     */
    public static <T> ResponseDTO<T> response(int code, String message, T data) {
        return new ResponseDTO(code, message, data, MDC.get(CommonConstant.TRACE_ID));
    }

    /**
     * 成功返回
     * @param
     * @return {@link ResponseDTO<T>}
     * @date 2024/6/25 09:18
     * @author zhehen.lu
     */
    public static <T> ResponseDTO<T> success() {
        return response(0, "success", null);
    }

    /**
     * 成功返回， 并传入数据
     * @param data
     * @return {@link ResponseDTO<T>}
     * @date 2024/6/25 09:19
     * @author zhehen.lu
     */
    public static <T> ResponseDTO<T> success(T data) {
        return response(0, "success", data);
    }

    /**
     * 错误返回
     * @param code
     * @param message
     * @param content
     * @return {@link ResponseDTO<T>}
     * @date 2024/6/25 09:19
     * @author zhehen.lu
     */
    public static <T> ResponseDTO<T> ofError(int code, String message, T content) {
        return ResponseDTO.response(code, message, content);
    }

    /**
     * 错误返回
     * @param errorCode
     * @return {@link ResponseDTO<T>}
     * @date 2024/6/25 09:19
     * @author zhehen.lu
     */
    public static <T> ResponseDTO<T> ofError(ErrorCode errorCode) {
        return ResponseDTO.response(errorCode.getCode(), errorCode.getMsg(), null);
    }

    /**
     * 错误返回
     * @param code
     * @param message
     * @return {@link ResponseDTO<T>}
     * @date 2024/6/25 09:19
     * @author zhehen.lu
     */
    public static <T> ResponseDTO<T> ofError(int code, String message) {
        return ResponseDTO.response(code, message, null);
    }

    /**
     * 错误返回
     * @param errorCode
     * @param message
     * @return {@link ResponseDTO<T>}
     * @date 2024/6/25 09:19
     * @author zhehen.lu
     */
    public static <T> ResponseDTO<T> ofError(ErrorCode errorCode, String message) {
        return ResponseDTO.response(errorCode.getCode(), message, null);
    }
}
