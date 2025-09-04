package com.zhora.dto.system;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhora.common.validator.group.AddGroup;
import com.zhora.common.validator.group.DefaultGroup;
import com.zhora.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典数据
 *
 * @author zhehen.lu
 */
@Data
@Schema(title = "字典数据")
public class SysDictDataDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@Schema(title = "id")
	@Null(message="{id.null}", groups = AddGroup.class)
	@NotNull(message="{id.require}", groups = UpdateGroup.class)
	private Long id;

	@Schema(title = "字典类型ID")
	@NotNull(message="{sysdict.type.require}", groups = DefaultGroup.class)
	private Long dictTypeId;

	@Schema(title = "字典标签")
	@NotBlank(message="{sysdict.label.require}", groups = DefaultGroup.class)
	private String dictLabel;

	@Schema(title = "字典值")
	private String dictValue;

	@Schema(title = "备注")
	private String remark;

	@Schema(title = "排序")
	@Min(value = 0, message = "{sort.number}", groups = DefaultGroup.class)
	private Integer sort;

	@Schema(title = "创建时间")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createDate;

	@Schema(title = "更新时间")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date updateDate;
}
