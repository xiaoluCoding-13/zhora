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
import com.zhora.dto.system.SysParamsDTO;
import com.zhora.service.system.ISysParamsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 参数管理
 *
 * @author zhehen.lu luzhehen@163.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("sys/params")
@Tag(name = "参数管理")
@AllArgsConstructor
public class SysParamsController {
    private final ISysParamsService sysParamsService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "paramCode", description = "参数编码", in = ParameterIn.QUERY, ref = "String")
    })
    @RequiresPermissions("sys:params:page")
    public ResponseDTO<PageData<SysParamsDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<SysParamsDTO> page = sysParamsService.page(params);

        return ResponseDTO.success(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:params:info")
    public ResponseDTO<SysParamsDTO> get(@PathVariable("id") Long id) {
        SysParamsDTO data = sysParamsService.get(id);

        return ResponseDTO.success(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:params:save")
    public ResponseDTO<Void> save(@RequestBody SysParamsDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysParamsService.save(dto);

        return ResponseDTO.success();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:params:update")
    public ResponseDTO<Void> update(@RequestBody SysParamsDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysParamsService.update(dto);

        return ResponseDTO.success();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:params:delete")
    public ResponseDTO<Void> delete(@RequestBody Long[] ids) {
        //效验数据
        ValidateUtil.notNull(ids, CommonCode.RENREN_SEND_ERROR);

        sysParamsService.delete(ids);

        return ResponseDTO.success();
    }

//    @GetMapping("export")
//    @Operation(summary = "导出")
//    @LogOperation("导出")
//    @RequiresPermissions("sys:params:export")
//    @Parameter(name = "paramCode", description = "参数编码", in = ParameterIn.QUERY, ref = "String")
//    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
//        List<SysParamsDTO> list = sysParamsService.list(params);
//
//        ExcelUtils.exportExcelToTarget(response, null, "参数管理", list, SysParamsExcel.class);
//    }

}
