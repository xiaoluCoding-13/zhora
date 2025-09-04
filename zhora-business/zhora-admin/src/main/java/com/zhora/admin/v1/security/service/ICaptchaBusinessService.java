package com.zhora.admin.v1.security.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 验证码
 *
 * @author zhehen.lu
 */
public interface ICaptchaBusinessService {

    /**
     * 图片验证码
     */
    void create(HttpServletResponse response, String uuid) throws IOException;

    /**
     * 验证码效验
     * @param uuid  uuid
     * @param code  验证码
     * @return  true：成功  false：失败
     */
    boolean validate(String uuid, String code);
}
