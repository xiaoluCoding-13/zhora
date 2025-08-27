package com.zhora.admin.v1.system.user.controller;

import com.zhora.admin.v1.system.user.business.ISysUserBusinessService;
import com.zhora.admin.v1.system.user.dto.SysUserListDTO;
import com.zhora.admin.v1.system.user.vo.SysUserListVO;
import com.zhora.common.dto.ResponseDTO;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息
 *
 * @author zhehen.lu
 * @date 2025/8/22 09:12
 */
@RestController
@CrossOrigin
@RequestMapping("/user/v1")
@Slf4j
public class SysUserController {

    @Autowired
    ISysUserBusinessService userBusinessService;

    @PostMapping("/list")
    public ResponseDTO<PageDataGridRespDTO<SysUserListDTO>> listPage(@RequestBody SysUserListVO listVO) {
        return ResponseDTO.success(userBusinessService.listPage(listVO));
    }

}
