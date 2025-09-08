package com.zhora.admin.v1.system.controller;

import com.zhora.admin.annotation.LogOperation;
import com.zhora.common.domain.ResponseDTO;
import com.zhora.common.domain.page.PageData;
import com.zhora.common.errcode.CommonCode;
import com.zhora.common.password.PasswordUtils;
import com.zhora.common.utils.ConvertUtils;
import com.zhora.common.utils.ValidateUtil;
import com.zhora.common.validator.ValidatorUtils;
import com.zhora.common.validator.group.AddGroup;
import com.zhora.common.validator.group.DefaultGroup;
import com.zhora.common.validator.group.UpdateGroup;
import com.zhora.constant.Constant;
import com.zhora.dto.system.PasswordDTO;
import com.zhora.dto.system.SecurityUser;
import com.zhora.dto.system.SysUserDTO;
import com.zhora.dto.system.UserDetail;
import com.zhora.service.system.ISysRoleUserService;
import com.zhora.service.system.ISysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 * @author zhehen.lu luzhehen@163.com
 */
@RestController
@RequestMapping("/sys/user")
@Tag(name = "用户管理")
@AllArgsConstructor
public class SysUserController {
    private final ISysUserService sysUserService;
    private final ISysRoleUserService sysRoleUserService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "username", description = "用户名", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "gender", description = "性别", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "deptId", description = "部门ID", in = ParameterIn.QUERY, ref = "String")
    })
    @RequiresPermissions("sys:user:page")
    public ResponseDTO<PageData<SysUserDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<SysUserDTO> page = sysUserService.page(params);

        return ResponseDTO.success(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:user:info")
    public ResponseDTO<SysUserDTO> get(@PathVariable("id") Long id) {
        SysUserDTO data = sysUserService.get(id);

        //用户角色列表
        List<Long> roleIdList = sysRoleUserService.getRoleIdList(id);
        data.setRoleIdList(roleIdList);

        return ResponseDTO.success(data);
    }

    @GetMapping("info")
    @Operation(summary = "登录用户信息")
    public ResponseDTO<SysUserDTO> info() {
        SysUserDTO data = ConvertUtils.sourceToTarget(SecurityUser.getUser(), SysUserDTO.class);
        return ResponseDTO.success(data);
    }

    @PutMapping("password")
    @Operation(summary = "修改密码")
    @LogOperation("修改密码")
    public ResponseDTO<Void> password(@RequestBody PasswordDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto);

        UserDetail user = SecurityUser.getUser();

        //原密码不正确
        if (!PasswordUtils.matches(dto.getPassword(), user.getPassword())) {
            return ResponseDTO.ofError(CommonCode.RENREN_SEND_ERROR);
        }

        sysUserService.updatePassword(user.getId(), dto.getNewPassword());

        return ResponseDTO.success();
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:user:save")
    public ResponseDTO<Void> save(@RequestBody SysUserDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysUserService.save(dto);

        return ResponseDTO.success();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:user:update")
    public ResponseDTO update(@RequestBody SysUserDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysUserService.update(dto);

        return ResponseDTO.success();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:user:delete")
    public ResponseDTO<Void> delete(@RequestBody Long[] ids) {
        //效验数据
        ValidateUtil.notNull(ids,CommonCode.RENREN_SEND_ERROR);

        sysUserService.deleteBatchIds(Arrays.asList(ids));

        return ResponseDTO.success();
    }

//    @GetMapping("export")
//    @Operation(summary = "导出")
//    @LogOperation("导出")
//    @RequiresPermissions("sys:user:export")
//    @Parameter(name = "username", description = "用户名", in = ParameterIn.QUERY, ref = "String")
//    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
//        List<SysUserDTO> list = sysUserService.list(params);
//
//        ExcelUtils.exportExcelToTarget(response, null, "用户管理", list, SysUserExcel.class);
//    }
}
