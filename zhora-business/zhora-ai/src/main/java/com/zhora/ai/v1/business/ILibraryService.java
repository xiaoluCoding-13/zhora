package com.zhora.ai.v1.business;

import com.zhora.ai.v1.dto.AiLibraryListDTO;
import com.zhora.ai.v1.vo.AiLibraryListVO;

import java.util.List;

/**
 * @author zhehen.lu
 * @date 2025/9/10 15:35
 */
public interface ILibraryService {

    List<AiLibraryListDTO> list(AiLibraryListVO listVO);

}
