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
import com.zhora.dto.system.SysDictDataDTO;
import com.zhora.service.system.ISysDictDataService;
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
 * 字典数据
 *
 * @author zhehen.lu luzhehen@163.com
 */
@RestController
@RequestMapping("sys/dict/data")
@Tag(name = "字典数据")
@AllArgsConstructor
public class SysDictDataController {
    private final ISysDictDataService sysDictDataService;

    @GetMapping("page")
    @Operation(summary = "字典数据")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "dictLabel", description = "字典标签", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "dictValue", description = "字典值", in = ParameterIn.QUERY, ref = "String")
    })
    @RequiresPermissions("sys:dict:page")
    public ResponseDTO<PageData<SysDictDataDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        //字典类型
        PageData<SysDictDataDTO> page = sysDictDataService.page(params);

        return ResponseDTO.success(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:dict:info")
    public ResponseDTO<SysDictDataDTO> get(@PathVariable("id") Long id) {
        SysDictDataDTO data = sysDictDataService.get(id);

        return ResponseDTO.success(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:dict:save")
    public ResponseDTO<Void> save(@RequestBody SysDictDataDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysDictDataService.save(dto);

        return ResponseDTO.success();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:dict:update")
    public ResponseDTO<Void> update(@RequestBody SysDictDataDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysDictDataService.update(dto);

        return ResponseDTO.success();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:dict:delete")
    public ResponseDTO<Void> delete(@RequestBody Long[] ids) {
        //效验数据
        ValidateUtil.notNull(ids, CommonCode.RENREN_SEND_ERROR);

        sysDictDataService.delete(ids);

        return ResponseDTO.success();
    }

}
