package com.zhora.enums.ai;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Actions {
  CREATE_USER("CREATE_USER", "创建用户"),
  DELETE_USER("DELETE_USER", "删除用户"),
  CREATE_DEPARTMENT("CREATE_DEPARTMENT", "创建部门"),
  DELETE_DEPARTMENT("DELETE_DEPARTMENT", "删除部门"),
  CREATE_POSITION("CREATE_POSITION", "创建岗位"),
  DELETE_POSITION("DELETE_POSITION", "删除岗位"),
  CREATE_ROLE("CREATE_ROLE", "创建角色"),
  DELETE_ROLE("CREATE_ROLE", "删除角色"),
  CREATE_PERMISSION("CREATE_PERMISSION", "创建权限"),
  DELETE_PERMISSION("DELETE_PERMISSION", "删除权限");
  public static final String INDEX_KEY = "action";
  private final String code;
  private final String content;
}
