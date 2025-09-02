package com.zhora.admin.shiro.rememberMe;

import com.zhora.admin.shiro.service.SysLoginService;
import com.zhora.admin.v1.system.role.dto.SysRoleDetailDTO;
import com.zhora.admin.v1.system.user.dto.SysUserDetailDTO;
import com.zhora.admin.v1.system.user.dto.SysUserListDTO;
import com.zhora.common.utils.ApplicationContextUtil;
import com.zhora.dto.system.SysRoleDTO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.CookieRememberMeManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 自定义CookieRememberMeManager
 *
 * @author ruoyi
 */
public class CustomCookieRememberMeManager extends CookieRememberMeManager {
    /**
     * 记住我时去掉角色的permissions权限字符串，防止http请求头过大。
     */
    @Override
    protected void rememberIdentity(Subject subject, PrincipalCollection principalCollection) {
        Map<SysRoleDetailDTO, Set<String>> rolePermissions = new HashMap<>();
        // 清除角色的permissions权限字符串
        for (Object principal : principalCollection) {
            if (principal instanceof SysUserDetailDTO) {
                List<SysRoleDetailDTO> roles = ((SysUserDetailDTO) principal).getRoles();
                for (SysRoleDetailDTO role : roles) {
                    rolePermissions.put(role, role.getPermissions());
                    role.setPermissions(null);
                }
            }
        }
        byte[] bytes = convertPrincipalsToBytes(principalCollection);
        // 恢复角色的permissions权限字符串
        for (Object principal : principalCollection) {
            if (principal instanceof SysUserDetailDTO) {
                List<SysRoleDetailDTO> roles = ((SysUserDetailDTO) principal).getRoles();
                for (SysRoleDetailDTO role : roles) {
                    role.setPermissions(rolePermissions.get(role));
                }
            }
        }
        rememberSerializedIdentity(subject, bytes);
    }

    /**
     * 取记住我身份时恢复角色permissions权限字符串。
     */
    @Override
    public PrincipalCollection getRememberedPrincipals(SubjectContext subjectContext) {
        PrincipalCollection principals = super.getRememberedPrincipals(subjectContext);
        if (principals == null || principals.isEmpty()) {
            return principals;
        }
        for (Object principal : principals) {
            if (principal instanceof SysUserDetailDTO) {
                ApplicationContextUtil.getBean(SysLoginService.class).setRolePermission((SysUserDetailDTO) principal);
            }
        }
        return principals;
    }
}
