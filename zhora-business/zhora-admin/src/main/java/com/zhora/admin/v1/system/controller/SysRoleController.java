package com.zhora.admin.v1.system.controller;

import com.zhora.admin.annotation.LogOperation;
import com.zhora.common.domain.ResponseDTO;
import com.zhora.common.domain.page.PageData;
import com.zhora.common.errcode.CommonCode;
import com.zhora.common.utils.ValidateUtil;
import com.zhora.common.validator.ValidatorUtils;
import com.zhora.common.validator.group.AddGroup;
import com.zhora.common.validator.group.DefaultGroup;
import com.zhora.common.validator.group.UpdateGroup;
import com.zhora.constant.Constant;
import com.zhora.dto.system.SysRoleDTO;
import com.zhora.service.system.ISysRoleDataScopeService;
import com.zhora.service.system.ISysRoleMenuService;
import com.zhora.service.system.ISysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author zhehen.lu luzhehen@163.com
 */
@RestController
@RequestMapping("/sys/role")
@Tag(name = "角色管理")
@AllArgsConstructor
public class SysRoleController {
    private final ISysRoleService sysRoleService;
    private final ISysRoleMenuService sysRoleMenuService;
    private final ISysRoleDataScopeService sysRoleDataScopeService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "name", description = "角色名", in = ParameterIn.QUERY, ref = "String")
    })
    @RequiresPermissions("sys:role:page")
    public ResponseDTO<PageData<SysRoleDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<SysRoleDTO> page = sysRoleService.page(params);

        return ResponseDTO.success(page);
    }

    @GetMapping("list")
    @Operation(summary = "列表")
    @RequiresPermissions("sys:role:list")
    public ResponseDTO<List<SysRoleDTO>> list() {
        List<SysRoleDTO> data = sysRoleService.list(new HashMap<>(1));

        return ResponseDTO.success(data);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:role:info")
    public ResponseDTO<SysRoleDTO> get(@PathVariable("id") Long id) {
        SysRoleDTO data = sysRoleService.get(id);

        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.getMenuIdList(id);
        data.setMenuIdList(menuIdList);

        //查询角色对应的数据权限
        List<Long> deptIdList = sysRoleDataScopeService.getDeptIdList(id);
        data.setDeptIdList(deptIdList);

        return ResponseDTO.success(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:role:save")
    public ResponseDTO<Void> save(@RequestBody SysRoleDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysRoleService.save(dto);

        return ResponseDTO.success();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:role:update")
    public ResponseDTO<Void> update(@RequestBody SysRoleDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysRoleService.update(dto);

        return ResponseDTO.success();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:role:delete")
    public ResponseDTO<Void> delete(@RequestBody Long[] ids) {
        //效验数据
        ValidateUtil.notNull(ids, CommonCode.RENREN_SEND_ERROR);

        sysRoleService.delete(ids);

        return ResponseDTO.success();
    }
}
