package com.zhora.admin.v1.system.user.controller;

import com.zhora.admin.v1.system.user.business.ISysUserBusinessService;
import com.zhora.admin.v1.system.user.dto.SysUserListDTO;
import com.zhora.admin.v1.system.user.vo.SysUserListVO;
import com.zhora.common.dto.ResponseDTO;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "用户管理", description = "用户的注册、登录、信息管理")
public class SysUserController {

    @Autowired
    ISysUserBusinessService userBusinessService;

    @Operation(summary = "根据条件分页查询用户列表")
    @PostMapping("/list")
    public ResponseDTO<PageDataGridRespDTO<SysUserListDTO>> listPage(@RequestBody SysUserListVO listVO) {
        return ResponseDTO.success(userBusinessService.listPage(listVO));
    }

}
