package com.zhora.admin.v1.system.controller;

import com.zhora.admin.annotation.LogOperation;
import com.zhora.common.domain.ResponseDTO;
import com.zhora.common.domain.page.PageData;
import com.zhora.common.errcode.CommonCode;
import com.zhora.common.utils.ValidateUtil;
import com.zhora.common.validator.ValidatorUtils;
import com.zhora.common.validator.group.DefaultGroup;
import com.zhora.common.validator.group.UpdateGroup;
import com.zhora.constant.Constant;
import com.zhora.dto.system.SysDictTypeDTO;
import com.zhora.entity.system.DictType;
import com.zhora.service.system.ISysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 字典类型
 *
 * @author zhehen.lu luzhehen@163.com
 */
@RestController
@RequestMapping("sys/dict/type")
@Tag(name = "字典类型")
@AllArgsConstructor
public class SysDictTypeController {
    private final ISysDictTypeService sysDictTypeService;

    @GetMapping("page")
    @Operation(summary = "字典类型")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "dictType", description = "字典类型", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "dictName", description = "字典名称", in = ParameterIn.QUERY, ref = "String")
    })
    @RequiresPermissions("sys:dict:page")
    public ResponseDTO<PageData<SysDictTypeDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        //字典类型
        PageData<SysDictTypeDTO> page = sysDictTypeService.page(params);

        return ResponseDTO.success(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:dict:info")
    public ResponseDTO<SysDictTypeDTO> get(@PathVariable("id") Long id) {
        SysDictTypeDTO data = sysDictTypeService.get(id);

        return ResponseDTO.success(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:dict:save")
    public ResponseDTO<Void> save(@RequestBody SysDictTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysDictTypeService.save(dto);

        return ResponseDTO.success();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:dict:update")
    public ResponseDTO<Void> update(@RequestBody SysDictTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysDictTypeService.update(dto);

        return ResponseDTO.success();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:dict:delete")
    public ResponseDTO<Void> delete(@RequestBody Long[] ids) {
        //效验数据
        ValidateUtil.notNull(ids, CommonCode.RENREN_SEND_ERROR);

        sysDictTypeService.delete(ids);

        return ResponseDTO.success();
    }

    @GetMapping("all")
    @Operation(summary = "所有字典数据")
    public ResponseDTO<List<DictType>> all() {
        List<DictType> list = sysDictTypeService.getAllList();

        return ResponseDTO.success(list);
    }

}
