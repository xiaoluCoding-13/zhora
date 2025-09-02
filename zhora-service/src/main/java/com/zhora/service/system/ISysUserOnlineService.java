package com.zhora.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import com.zhora.dto.system.SysUserOnlineDTO;
import com.zhora.dto.system.search.SysUserOnlineSearchDTO;
import com.zhora.entity.system.SysUserOnlineEntity;

import java.util.Date;
import java.util.List;

/**
 * 在线用户记录(sys_user_online)表服务接口
 *
 * @author zhehen.lu
 * @since 2025-08-28 17:26:49
 */
public interface ISysUserOnlineService extends IService<SysUserOnlineEntity> {

    PageDataGridRespDTO<SysUserOnlineDTO> listPage(SysUserOnlineSearchDTO searchDTO);

    void create(SysUserOnlineDTO dto);

    SysUserOnlineDTO getDetailById(String id);

    SysUserOnlineDTO getDetail(SysUserOnlineSearchDTO searchDTO);

    void updateById(SysUserOnlineDTO dto);

    List<SysUserOnlineDTO> list(SysUserOnlineSearchDTO searchDTO);

    /**
     * 清理用户缓存
     *
     * @param loginName 登录名称
     * @param sessionId 会话ID
     */
    public void removeUserCache(String loginName, String sessionId);

    /**
     * 查询会话集合
     *
     * @param expiredDate 有效期
     * @return 会话集合
     */
    public List<SysUserOnlineDTO> selectOnlineByExpired(Date expiredDate);

    /**
     * 通过会话序号删除信息
     *
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    public void batchDeleteOnline(List<String> sessions);
}
