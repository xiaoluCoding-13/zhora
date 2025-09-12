package com.zhora.service.ai.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhora.common.domain.page.PageDataGridRespDTO;
import com.zhora.common.errcode.CommonCode;
import com.zhora.common.utils.ValidateUtil;
import com.zhora.db.common.util.PageDataGridRespUtil;
import com.zhora.db.common.util.PageQueryUtil;
import com.zhora.dto.ai.AiLibraryDTO;
import com.zhora.dto.ai.search.AiLibrarySearchDTO;
import com.zhora.entity.ai.AiLibraryEntity;
import com.zhora.mapper.ai.AiLibraryMapper;
import com.zhora.service.ai.IAiLibraryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 知识库(ai_library)表服务实现类
 *
 * @author zhehen.lu
 * @since 2025-09-10 15:25:04
 */
@Service
public class AiLibraryServiceImpl extends ServiceImpl<AiLibraryMapper, AiLibraryEntity> implements IAiLibraryService {

    /**
     * 根据条件分页查询知识库列表
     *
     * @param searchDTO
     * @return {@link PageDataGridRespDTO< AiLibraryDTO>}
     * @date 2025-09-10 15:25:04
     * @author zhehen.lu
     */
    @Override
    public PageDataGridRespDTO<AiLibraryDTO> listPage(AiLibrarySearchDTO searchDTO) {
        ValidateUtil.notNull(searchDTO, CommonCode.PARMA_NOT_NULL);

        LambdaQueryChainWrapper<AiLibraryEntity> wrapper = getWrapper(searchDTO);

        PageQueryUtil<AiLibraryEntity, AiLibrarySearchDTO> pageQueryUtil = new PageQueryUtil<>(
                AiLibraryEntity.class,
                PageQueryUtil.TypeEnum.PARAM_PRIORITY_DEFAULT_PRIMARY,
                searchDTO
        );

        IPage<AiLibraryEntity> page = wrapper.page(pageQueryUtil.buildPageObj());

        return PageDataGridRespUtil.convert(page, AiLibraryDTO.class);
    }

    /**
     * 创建知识库
     *
     * @param dto
     * @date 2025-09-10 15:25:04
     * @author zhehen.lu
     */
    @Override
    public void create(AiLibraryDTO dto) {
        ValidateUtil.notNull(dto, CommonCode.PARMA_NOT_NULL);
        AiLibraryEntity entity = BeanUtil.copyProperties(dto, AiLibraryEntity.class);

        save(entity);
    }

    /**
     * 依据ID获取知识库详情
     *
     * @param id
     * @return {@link AiLibraryDTO}
     * @date 2025-09-10 15:25:04
     * @author zhehen.lu
     */
    @Override
    public AiLibraryDTO getDetailById(Long id) {
        AiLibrarySearchDTO searchDTO = new AiLibrarySearchDTO();
        searchDTO.setId(id);
        searchDTO.setDelFlag(Boolean.FALSE);

        return getDetail(searchDTO);
    }

    /**
     * 获取知识库详情
     *
     * @param searchDTO
     * @return {@link AiLibraryDTO}
     * @date 2025-09-10 15:25:04
     * @author zhehen.lu
     */
    @Override
    public AiLibraryDTO getDetail(AiLibrarySearchDTO searchDTO) {
        LambdaQueryChainWrapper<AiLibraryEntity> wrapper = getWrapper(searchDTO);
        AiLibraryEntity entity = wrapper
                .last("LIMIT 1")
                .one();

        return BeanUtil.copyProperties(entity, AiLibraryDTO.class);
    }

    /**
     * 依据ID更新知识库数据
     *
     * @param dto
     * @date 2025-09-10 15:25:04
     * @author zhehen.lu
     */
    @Override
    public void updateById(AiLibraryDTO dto) {
        ValidateUtil.notNull(dto, CommonCode.PARMA_NOT_NULL);
        AiLibraryEntity entity = BeanUtil.copyProperties(dto, AiLibraryEntity.class);

        updateById(entity);
    }

    /**
     * 获取知识库列表
     *
     * @param searchDTO
     * @return {@link List< AiLibraryDTO>}
     * @date 2025-09-10 15:25:04
     * @author zhehen.lu
     */
    @Override
    public List<AiLibraryDTO> list(AiLibrarySearchDTO searchDTO) {
        ValidateUtil.notNull(searchDTO, CommonCode.PARMA_NOT_NULL);

        LambdaQueryChainWrapper<AiLibraryEntity> wrapper = getWrapper(searchDTO);

        List<AiLibraryEntity> entityList = wrapper.list();

        return BeanUtil.copyToList(entityList, AiLibraryDTO.class);
    }

    /**
     * 获取知识库的Wrapper对象
     *
     * @param searchDTO
     * @return {@link LambdaQueryChainWrapper< AiLibraryEntity>}
     * @date 2025-09-10 15:25:04
     * @author zhehen.lu
     */
    private LambdaQueryChainWrapper<AiLibraryEntity> getWrapper(AiLibrarySearchDTO searchDTO) {
        return lambdaQuery();
    }

}
