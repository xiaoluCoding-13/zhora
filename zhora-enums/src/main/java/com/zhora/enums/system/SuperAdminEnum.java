package com.zhora.enums.system;

/**
 * 超级管理员枚举
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
public enum SuperAdminEnum {
    YES(1),
    NO(0);

    private int value;

    SuperAdminEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}