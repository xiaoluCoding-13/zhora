package com.zhora.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhora.entity.system.SysUserOnlineEntity;

import java.util.List;

/**
 * 在线用户记录(sys_user_online)表数据库访问层
 *
 * @author zhehen.lu
 * @since 2025-08-28 17:26:31
 */
public interface SysUserOnlineMapper extends BaseMapper<SysUserOnlineEntity> {

    /**
     * 通过会话序号查询信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public SysUserOnlineEntity selectOnlineById(String sessionId);

    /**
     * 通过会话序号删除信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public int deleteOnlineById(String sessionId);

    /**
     * 保存会话信息
     *
     * @param online 会话信息
     * @return 结果
     */
    public int saveOnline(SysUserOnlineEntity online);

    /**
     * 查询会话集合
     *
     * @param userOnline 会话参数
     * @return 会话集合
     */
    public List<SysUserOnlineEntity> selectUserOnlineList(SysUserOnlineEntity userOnline);

    /**
     * 查询过期会话集合
     *
     * @param lastAccessTime 过期时间
     * @return 会话集合
     */
    public List<SysUserOnlineEntity> selectOnlineByExpired(String lastAccessTime);

}

