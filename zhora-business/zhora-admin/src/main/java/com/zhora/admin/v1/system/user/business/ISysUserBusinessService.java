package com.zhora.admin.v1.system.user.business;

import com.zhora.admin.v1.system.user.dto.SysUserListDTO;
import com.zhora.admin.v1.system.user.vo.SysUserListVO;
import com.zhora.common.dto.page.PageDataGridRespDTO;

/**
 * 用户 业务层
 * 
 * @author ruoyi
 */
public interface ISysUserBusinessService {

    PageDataGridRespDTO<SysUserListDTO> listPage(SysUserListVO listVO);
}
