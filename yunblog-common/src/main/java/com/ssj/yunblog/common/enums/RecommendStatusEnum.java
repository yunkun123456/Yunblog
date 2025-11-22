package com.ssj.yunblog.common.enums;

import lombok.Getter;

@Getter
public enum RecommendStatusEnum {

    RECOMMEND("1", "推荐"), UN_RECOMMEND("0", "未推荐");

    private final String code;

    private final String desc;

    RecommendStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RecommendStatusEnum getByCode(String code) {
        for (RecommendStatusEnum deleteStatusEnum : RecommendStatusEnum.values()) {
            if (deleteStatusEnum.code.equals(code)) {
                return deleteStatusEnum;
            }
        }
        return null;
    }
}
