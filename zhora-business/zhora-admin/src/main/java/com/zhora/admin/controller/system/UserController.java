package com.zhora.admin.controller;

import com.zhora.common.dto.ResponseDTO;
import com.zhora.dto.system.SysUserDTO;
import com.zhora.service.system.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息
 *
 * @author zhehen.lu
 * @date 2025/8/22 09:12
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    ISysUserService sysUserService;

    @GetMapping("/list")
    public ResponseDTO<List<SysUserDTO>> createNewConversation() {
        return ResponseDTO.success(sysUserService.selectUserList(null));
    }

}
