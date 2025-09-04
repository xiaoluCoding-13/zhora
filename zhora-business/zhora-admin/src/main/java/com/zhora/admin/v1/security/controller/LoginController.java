package com.zhora.admin.v1.security.controller;

import com.zhora.admin.v1.security.dto.LoginDTO;
import com.zhora.admin.v1.security.enmus.LoginOperationEnum;
import com.zhora.admin.v1.security.enmus.LoginStatusEnum;
import com.zhora.admin.v1.security.service.ICaptchaBusinessService;
import com.zhora.admin.v1.security.service.ISysUserTokenBusinessService;
import com.zhora.common.domain.ResponseDTO;
import com.zhora.common.errcode.CommonCode;
import com.zhora.common.exception.BaseException;
import com.zhora.common.password.PasswordUtils;
import com.zhora.common.utils.IpUtils;
import com.zhora.common.utils.ValidateUtil;
import com.zhora.common.validator.ValidatorUtils;
import com.zhora.dto.system.SecurityUser;
import com.zhora.dto.system.SysUserDTO;
import com.zhora.dto.system.UserDetail;
import com.zhora.entity.log.SysLogLoginEntity;
import com.zhora.enums.system.UserStatusEnum;
import com.zhora.service.log.ISysLogLoginService;
import com.zhora.service.system.ISysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 登录
 *
 * @author zhehen.lu
 */
@RestController
@Tag(name = "登录管理")
@AllArgsConstructor
public class LoginController {
    private final ISysUserService sysUserService;
    private final ISysUserTokenBusinessService sysUserTokenService;
    private final ICaptchaBusinessService captchaService;
    private final ISysLogLoginService sysLogLoginService;

    @GetMapping("captcha")
    @Operation(summary = "验证码")
    @Parameter(in = ParameterIn.QUERY, ref = "string", name = "uuid", required = true)
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        //uuid不能为空
        ValidateUtil.notNull(uuid,CommonCode.RENREN_SEND_ERROR);

        //生成验证码
        captchaService.create(response, uuid);
    }

    @PostMapping("login")
    @Operation(summary = "登录")
    public ResponseDTO<Map<String, Object>> login(HttpServletRequest request, @RequestBody LoginDTO login) {
        //效验数据
        ValidatorUtils.validateEntity(login);

        //验证码是否正确
        boolean flag = captchaService.validate(login.getUuid(), login.getCaptcha());
        if (!flag) {
            return ResponseDTO.ofError(CommonCode.RENREN_SEND_ERROR);
        }

        //用户信息
        SysUserDTO user = sysUserService.getByUsername(login.getUsername());

        SysLogLoginEntity log = new SysLogLoginEntity();
        log.setOperation(LoginOperationEnum.LOGIN.value());
        log.setCreateDate(new Date());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));

        //用户不存在
        if (user == null) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreatorName(login.getUsername());
            sysLogLoginService.save(log);

            throw new BaseException(CommonCode.RENREN_SEND_ERROR);
        }

        //密码错误
        if (!PasswordUtils.matches(login.getPassword(), user.getPassword())) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreator(user.getId());
            log.setCreatorName(user.getUsername());
            sysLogLoginService.save(log);

            throw new BaseException(CommonCode.RENREN_SEND_ERROR);
        }

        //账号停用
        if (user.getStatus() == UserStatusEnum.DISABLE.value()) {
            log.setStatus(LoginStatusEnum.LOCK.value());
            log.setCreator(user.getId());
            log.setCreatorName(user.getUsername());
            sysLogLoginService.save(log);

            throw new BaseException(CommonCode.RENREN_SEND_ERROR);
        }

        //登录成功
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(user.getId());
        log.setCreatorName(user.getUsername());
        sysLogLoginService.save(log);

        return sysUserTokenService.createToken(user.getId());
    }

    @PostMapping("logout")
    @Operation(summary = "退出")
    public ResponseDTO<Void> logout(HttpServletRequest request) {
        UserDetail user = SecurityUser.getUser();

        //退出
        sysUserTokenService.logout(user.getId());

        //用户信息
        SysLogLoginEntity log = new SysLogLoginEntity();
        log.setOperation(LoginOperationEnum.LOGOUT.value());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setIp(IpUtils.getIpAddr(request));
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(user.getId());
        log.setCreatorName(user.getUsername());
        log.setCreateDate(new Date());
        sysLogLoginService.save(log);

        return ResponseDTO.success();
    }

}
