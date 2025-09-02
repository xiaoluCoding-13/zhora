package com.zhora.service.system.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import com.zhora.common.errcode.CommonCode;
import com.zhora.common.utils.DateUtils;
import com.zhora.common.utils.StringUtils;
import com.zhora.common.utils.ValidateUtil;
import com.zhora.common.utils.spring.SpringUtils;
import com.zhora.constant.ShiroConstants;
import com.zhora.db.common.util.PageDataGridRespUtil;
import com.zhora.db.common.util.PageQueryUtil;
import com.zhora.dto.system.SysUserOnlineDTO;
import com.zhora.dto.system.search.SysUserOnlineSearchDTO;
import com.zhora.entity.system.SysUserOnlineEntity;
import com.zhora.mapper.system.SysUserOnlineMapper;
import com.zhora.service.system.ISysUserOnlineService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.Deque;
import java.util.List;

/**
 * 在线用户记录(sys_user_online)表服务实现类
 *
 * @author zhehen.lu
 * @since 2025-08-28 17:27:04
 */
@Service
public class SysUserOnlineServiceImpl extends ServiceImpl<SysUserOnlineMapper, SysUserOnlineEntity> implements ISysUserOnlineService {

    /**
     * 根据条件分页查询在线用户记录列表
     *
     * @param searchDTO
     * @return {@link PageDataGridRespDTO< SysUserOnlineDTO>}
     * @date 2025-08-28 17:27:04
     * @author zhehen.lu
     */
    @Override
    public PageDataGridRespDTO<SysUserOnlineDTO> listPage(SysUserOnlineSearchDTO searchDTO) {
        ValidateUtil.notNull(searchDTO, CommonCode.PARMA_NOT_NULL);

        LambdaQueryChainWrapper<SysUserOnlineEntity> wrapper = getWrapper(searchDTO);

        PageQueryUtil<SysUserOnlineEntity, SysUserOnlineSearchDTO> pageQueryUtil = new PageQueryUtil<>(
                SysUserOnlineEntity.class,
                PageQueryUtil.TypeEnum.PARAM_PRIORITY_DEFAULT_PRIMARY,
                searchDTO
        );

        IPage<SysUserOnlineEntity> page = wrapper.page(pageQueryUtil.buildPageObj());

        return PageDataGridRespUtil.convert(page, SysUserOnlineDTO.class);
    }

    /**
     * 创建在线用户记录
     *
     * @param dto
     * @date 2025-08-28 17:27:04
     * @author zhehen.lu
     */
    @Override
    public void create(SysUserOnlineDTO dto) {
        ValidateUtil.notNull(dto, CommonCode.PARMA_NOT_NULL);
        SysUserOnlineEntity entity = BeanUtil.copyProperties(dto, SysUserOnlineEntity.class);

        save(entity);
    }

    /**
     * 依据ID获取在线用户记录详情
     *
     * @param id
     * @return {@link SysUserOnlineDTO}
     * @date 2025-08-28 17:27:04
     * @author zhehen.lu
     */
    @Override
    public SysUserOnlineDTO getDetailById(String id) {
        SysUserOnlineSearchDTO searchDTO = new SysUserOnlineSearchDTO();
        searchDTO.setSessionId(id);

        return getDetail(searchDTO);
    }

    /**
     * 获取在线用户记录详情
     *
     * @param searchDTO
     * @return {@link SysUserOnlineDTO}
     * @date 2025-08-28 17:27:04
     * @author zhehen.lu
     */
    @Override
    public SysUserOnlineDTO getDetail(SysUserOnlineSearchDTO searchDTO) {
        LambdaQueryChainWrapper<SysUserOnlineEntity> wrapper = getWrapper(searchDTO);
        SysUserOnlineEntity entity = wrapper
                .last("LIMIT 1")
                .one();

        return BeanUtil.copyProperties(entity, SysUserOnlineDTO.class);
    }

    /**
     * 依据ID更新在线用户记录数据
     *
     * @param dto
     * @date 2025-08-28 17:27:04
     * @author zhehen.lu
     */
    @Override
    public void updateById(SysUserOnlineDTO dto) {
        ValidateUtil.notNull(dto, CommonCode.PARMA_NOT_NULL);
        SysUserOnlineEntity entity = BeanUtil.copyProperties(dto, SysUserOnlineEntity.class);

        updateById(entity);
    }

    /**
     * 获取在线用户记录列表
     *
     * @param searchDTO
     * @return {@link List< SysUserOnlineDTO>}
     * @date 2025-08-28 17:27:04
     * @author zhehen.lu
     */
    @Override
    public List<SysUserOnlineDTO> list(SysUserOnlineSearchDTO searchDTO) {
        ValidateUtil.notNull(searchDTO, CommonCode.PARMA_NOT_NULL);

        LambdaQueryChainWrapper<SysUserOnlineEntity> wrapper = getWrapper(searchDTO);

        List<SysUserOnlineEntity> entityList = wrapper.list();

        return BeanUtil.copyToList(entityList, SysUserOnlineDTO.class);
    }

    @Override
    public void removeUserCache(String loginName, String sessionId) {
        EhCacheManager ehCacheManager = SpringUtils.getBean(EhCacheManager.class);
        Cache<String, Deque<Serializable>> cache = ehCacheManager.getCache(ShiroConstants.SYS_USERCACHE);
        Deque<Serializable> deque = cache.get(loginName);
        if (StringUtils.isEmpty(deque) || deque.size() == 0) {
            return;
        }
        deque.remove(sessionId);
    }

    @Override
    public List<SysUserOnlineDTO> selectOnlineByExpired(Date expiredDate) {
        String lastAccessTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, expiredDate);
        return BeanUtil.copyToList(this.baseMapper.selectOnlineByExpired(lastAccessTime), SysUserOnlineDTO.class);
    }

    @Override
    public void batchDeleteOnline(List<String> sessions) {
        for (String sessionId : sessions) {
            SysUserOnlineSearchDTO searchDTO = new SysUserOnlineSearchDTO();
            searchDTO.setSessionId(sessionId);
            SysUserOnlineDTO userOnline = getDetail(searchDTO);
            if (StringUtils.isNotNull(userOnline)) {
                this.baseMapper.deleteOnlineById(sessionId);
            }
        }
    }

    /**
     * 获取在线用户记录的Wrapper对象
     *
     * @param searchDTO
     * @return {@link LambdaQueryChainWrapper< SysUserOnlineEntity>}
     * @date 2025-08-28 17:27:04
     * @author zhehen.lu
     */
    private LambdaQueryChainWrapper<SysUserOnlineEntity> getWrapper(SysUserOnlineSearchDTO searchDTO) {
        return lambdaQuery()
                .eq(org.apache.commons.lang3.StringUtils.isNoneEmpty(searchDTO.getSessionId()), SysUserOnlineEntity::getSessionId, searchDTO.getSessionId());
    }

}
