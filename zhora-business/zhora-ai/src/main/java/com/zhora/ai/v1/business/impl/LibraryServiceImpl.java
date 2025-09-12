package com.zhora.ai.v1.business.impl;

import com.zhora.ai.v1.business.ILibraryService;
import com.zhora.ai.v1.dto.AiLibraryListDTO;
import com.zhora.ai.v1.vo.AiLibraryListVO;
import com.zhora.common.utils.ConvertUtils;
import com.zhora.dto.ai.search.AiLibrarySearchDTO;
import com.zhora.service.ai.IAiLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhehen.lu
 * @date 2025/9/10 15:41
 */
@Service
@Slf4j
public class LibraryServiceImpl implements ILibraryService {

    @Autowired
    private IAiLibraryService aiLibraryService;

    @Override
    public List<AiLibraryListDTO> list(AiLibraryListVO listVO) {
        AiLibrarySearchDTO searchDTO = ConvertUtils.sourceToTarget(listVO, AiLibrarySearchDTO.class);
        searchDTO.setDelFlag(Boolean.FALSE);
        return ConvertUtils.sourceToTarget(aiLibraryService.list(searchDTO), AiLibraryListDTO.class);
    }

}
