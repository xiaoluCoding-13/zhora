package com.zhora.admin.manager.factory;

import com.zhora.admin.shiro.session.OnlineSession;
import com.zhora.common.utils.AddressUtils;
import com.zhora.common.utils.ApplicationContextUtil;
import com.zhora.common.utils.StringUtils;
import com.zhora.common.utils.spring.SpringUtils;
import com.zhora.constant.Constants;
import com.zhora.dto.system.SysUserOnlineDTO;
import com.zhora.service.system.ISysUserOnlineService;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author liuhulu
 */
@Slf4j
public class AsyncFactory {

    /**
     * 同步session到数据库
     *
     * @param session 在线用户会话
     * @return 任务task
     */
    public static TimerTask syncSessionToDb(final OnlineSession session) {
        return new TimerTask() {
            @Override
            public void run() {
                SysUserOnlineDTO online = new SysUserOnlineDTO();
                online.setSessionId(String.valueOf(session.getId()));
                online.setDeptName(session.getDeptName());
                online.setLoginName(session.getLoginName());
                online.setStartTimestamp(session.getStartTimestamp());
                online.setLastAccessTime(session.getLastAccessTime());
                online.setExpireTime(session.getTimeout());
                online.setIpaddr(session.getHost());
                online.setLoginLocation(AddressUtils.getRealAddressByIP(session.getHost()));
                online.setBrowser(session.getBrowser());
                online.setOs(session.getOs());
                online.setStatus(session.getStatus().getInfo());
                ApplicationContextUtil.getBean(ISysUserOnlineService.class).create(online);

            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
//    public static TimerTask recordOper(final SysOperLog operLog) {
//        return new TimerTask() {
//            @Override
//            public void run() {
//                // 远程查询操作地点
//                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
//                SpringUtils.getBean(ISysOperLogService.class).insertOperlog(operLog);
//            }
//        };
//    }

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message, final Object... args) {
//        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
//        final String ip = ShiroUtils.getIp();
//        return new TimerTask() {
//            @Override
//            public void run() {
//                String address = AddressUtils.getRealAddressByIP(ip);
//                StringBuilder s = new StringBuilder();
//                s.append(LogUtils.getBlock(ip));
//                s.append(address);
//                s.append(LogUtils.getBlock(username));
//                s.append(LogUtils.getBlock(status));
//                s.append(LogUtils.getBlock(message));
//                // 打印信息到日志
//                log.info(s.toString(), args);
//                // 获取客户端操作系统
//                String os = userAgent.getOperatingSystem().getName();
//                // 获取客户端浏览器
//                String browser = userAgent.getBrowser().getName();
//                // 封装对象
//                SysLogininfor logininfor = new SysLogininfor();
//                logininfor.setLoginName(username);
//                logininfor.setIpaddr(ip);
//                logininfor.setLoginLocation(address);
//                logininfor.setBrowser(browser);
//                logininfor.setOs(os);
//                logininfor.setMsg(message);
//                // 日志状态
//                if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
//                    logininfor.setStatus(Constants.SUCCESS);
//                } else if (Constants.LOGIN_FAIL.equals(status)) {
//                    logininfor.setStatus(Constants.FAIL);
//                }
//                // 插入数据
//                SpringUtils.getBean(SysLogininforServiceImpl.class).insertLogininfor(logininfor);
//            }
//        };
        return null;
    }
}
