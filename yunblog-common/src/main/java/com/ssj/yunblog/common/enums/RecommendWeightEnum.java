package com.ssj.yunblog.common.enums;

import lombok.Getter;

/**
 * 推荐指数权重
 *
 * @author: yunkun
 * @Date: 2025/11/22
 */
@Getter
public enum RecommendWeightEnum {

    HIGH("high", 3.0),
    MEDIUM("medium", 2.0),
    LOW("low", 1.0);

    private final double value;
    private final String code;

    RecommendWeightEnum(String code, double value) {
        this.value = value;
        this.code = code;
    }

    public static RecommendWeightEnum getByCode(String code) {
        for (RecommendWeightEnum weightEnum : RecommendWeightEnum.values()) {
            if (weightEnum.code.equals(code)) {
                return weightEnum;
            }
        }
        return null;
    }
}
