package com.zhora.service.system.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import com.zhora.common.errcode.CommonCode;
import com.zhora.common.utils.NumberUtil;
import com.zhora.common.utils.ValidateUtil;
import com.zhora.db.common.util.PageDataGridRespUtil;
import com.zhora.db.common.util.PageQueryUtil;
import com.zhora.dto.system.SysUserDTO;
import com.zhora.dto.system.search.SysUserSearchDTO;
import com.zhora.entity.system.SysUserEntity;
import com.zhora.mapper.system.SysUserMapper;
import com.zhora.service.system.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 用户 业务层处理
 * 
 * @author ruoyi
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements ISysUserService  {

    /**
     * 根据条件分页查询用户列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    public PageDataGridRespDTO<SysUserDTO> selectUserList(SysUserSearchDTO searchDTO) {
        ValidateUtil.notNull(searchDTO, CommonCode.PARMA_NOT_NULL);

        LambdaQueryChainWrapper<SysUserEntity> wrapper = getWrapper(searchDTO);

        PageQueryUtil<SysUserEntity, SysUserSearchDTO> pageQueryUtil = new PageQueryUtil<>(
                SysUserEntity.class,
                PageQueryUtil.TypeEnum.PARAM_PRIORITY_DEFAULT_PRIMARY,
                searchDTO
        );

        IPage<SysUserEntity> page = wrapper.page(pageQueryUtil.buildPageObj());

        return PageDataGridRespUtil.convert(page, SysUserDTO.class);
    }

    /**
     * 获取Wrapper
     * @param searchDTO
     * @return {@link LambdaQueryChainWrapper< SysUserEntity>}
     * @date 2025/8/21 20:12
     * @author zhehen.lu
     */
    private LambdaQueryChainWrapper<SysUserEntity> getWrapper(SysUserSearchDTO searchDTO) {
        return lambdaQuery()
                .eq(NumberUtil.isGtZero(searchDTO.getUserId()), SysUserEntity::getUserId, searchDTO.getUserId())
                .eq(StringUtils.isNoneEmpty(searchDTO.getUserName()), SysUserEntity::getUserName, searchDTO.getUserName())
                .eq(searchDTO.getDelFlag() != null, SysUserEntity::getDelFlag, searchDTO.getDelFlag());
    }

}
