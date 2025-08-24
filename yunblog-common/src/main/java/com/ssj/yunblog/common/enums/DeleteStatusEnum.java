package com.ssj.yunblog.common.enums;

import lombok.Getter;

@Getter
public enum DeleteStatusEnum {

    DELETED("1", "已删除"), UN_DELETED("0", "未删除");

    private String code;

    private String desc;

    DeleteStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static DeleteStatusEnum getByCode(String code) {
        for (DeleteStatusEnum deleteStatusEnum : DeleteStatusEnum.values()) {
            if (deleteStatusEnum.code.equals(code)) {
                return deleteStatusEnum;
            }
        }
        return null;
    }
}
