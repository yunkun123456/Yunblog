package com.ssj.yunblog.common.enums;

/**
 * 推荐指数权重
 *
 * @author: yunkun
 * @Date: 2025/11/22
 */
public enum RecommendWeightEnum {

    HIGH(3.0),
    MEDIUM(2.0),
    LOW(1.0);

    private final double value;

    RecommendWeightEnum(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
