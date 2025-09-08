package com.zhora.admin.v1.system.controller;

import com.zhora.common.domain.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页提示
 *
 * @author zhehen.lu luzhehen@163.com
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public ResponseDTO<String> index(){
        String tips = "你好，renren-admin已启动，请启动renren-ui，才能访问页面！";
        return ResponseDTO.success(tips);
    }
}
