package com.zhora.admin.shiro.service;

import com.zhora.admin.shiro.session.OnlineSession;
import com.zhora.common.utils.StringUtils;
import com.zhora.dto.system.SysUserOnlineDTO;
import com.zhora.dto.system.search.SysUserOnlineSearchDTO;
import com.zhora.service.system.ISysUserOnlineService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 会话db操作处理
 *
 * @author ruoyi
 */
@Component
public class SysShiroService {
    @Autowired
    private ISysUserOnlineService onlineService;

    /**
     * 删除会话
     *
     * @param onlineSession 会话信息
     */
    public void deleteSession(OnlineSession onlineSession) {
        onlineService.removeById(String.valueOf(onlineSession.getId()));
    }

    /**
     * 获取会话信息
     *
     * @param sessionId
     * @return
     */
    public Session getSession(Serializable sessionId) {
        SysUserOnlineSearchDTO searchDTO = new SysUserOnlineSearchDTO();
        searchDTO.setSessionId(String.valueOf(sessionId));

        SysUserOnlineDTO userOnline = onlineService.getDetail(searchDTO);
        return StringUtils.isNull(userOnline) ? null : createSession(userOnline);
    }

    public Session createSession(SysUserOnlineDTO userOnline) {
        OnlineSession onlineSession = new OnlineSession();
        if (StringUtils.isNotNull(userOnline)) {
            onlineSession.setId(userOnline.getSessionId());
            onlineSession.setHost(userOnline.getIpaddr());
            onlineSession.setBrowser(userOnline.getBrowser());
            onlineSession.setOs(userOnline.getOs());
            onlineSession.setDeptName(userOnline.getDeptName());
            onlineSession.setLoginName(userOnline.getLoginName());
            onlineSession.setStartTimestamp(userOnline.getStartTimestamp());
            onlineSession.setLastAccessTime(userOnline.getLastAccessTime());
            onlineSession.setTimeout(userOnline.getExpireTime());
        }
        return onlineSession;
    }
}
