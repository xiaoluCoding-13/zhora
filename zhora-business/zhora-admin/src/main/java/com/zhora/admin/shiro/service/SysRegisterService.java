package com.zhora.admin.shiro.service;

import cn.hutool.core.bean.BeanUtil;
import com.zhora.admin.manager.AsyncManager;
import com.zhora.admin.manager.factory.AsyncFactory;
import com.zhora.admin.shiro.util.ShiroUtils;
import com.zhora.admin.v1.system.user.vo.SysUserRegisterVO;
import com.zhora.common.exception.BaseException;
import com.zhora.common.utils.DateUtils;
import com.zhora.common.utils.MessageUtils;
import com.zhora.common.utils.ServletUtils;
import com.zhora.common.utils.StringUtils;
import com.zhora.constant.Constants;
import com.zhora.constant.ShiroConstants;
import com.zhora.constant.UserConstants;
import com.zhora.dto.system.SysUserDTO;
import com.zhora.service.system.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 注册校验方法
 *
 * @author ruoyi
 */
@Component
public class SysRegisterService {
    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    /**
     * 注册
     */
    public String register(SysUserRegisterVO user) {
        String msg = "", loginName = user.getLoginName(), password = user.getPassword();

        if (ShiroConstants.CAPTCHA_ERROR.equals(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
            msg = "验证码错误";
        } else if (StringUtils.isEmpty(loginName)) {
            msg = "用户名不能为空";
        } else if (StringUtils.isEmpty(password)) {
            msg = "用户密码不能为空";
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            msg = "密码长度必须在5到20个字符之间";
        } else if (loginName.length() < UserConstants.USERNAME_MIN_LENGTH
                || loginName.length() > UserConstants.USERNAME_MAX_LENGTH) {
            msg = "账户长度必须在2到20个字符之间";
        }
//        else if (!userService.checkLoginNameUnique(user)) {
//            msg = "保存用户'" + loginName + "'失败，注册账号已存在";
//        }
        else {
            user.setPwdUpdateDate(DateUtils.getNowDate());
            user.setUserName(loginName);
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(loginName, password, user.getSalt()));

            SysUserDTO sysUserDTO = BeanUtil.copyProperties(user, SysUserDTO.class);
            sysUserDTO.setUserType(UserConstants.REGISTER_USER_TYPE);
            try {
                userService.create(sysUserDTO);
            } catch (Exception e) {
                msg = "注册失败,请联系系统管理人员";
            }
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.REGISTER, MessageUtils.message("user.register.success")));
        }
        return msg;
    }
}
