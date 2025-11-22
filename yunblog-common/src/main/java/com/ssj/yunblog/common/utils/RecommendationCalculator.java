package com.ssj.yunblog.common.utils;

import com.ssj.yunblog.common.enums.RecommendWeightEnum;

public class RecommendationCalculator {

    /**
     * 权重系数
     */
    private final static Double WEIGHT_COEFFICIENT = 1.2;

    /**
     * 时间权重系数
     */
    private final static Double TIME_COEFFICIENT = 0.5;

    /**
     * 计算推荐指数
     *
     * @param weight     权重等级
     * @param createTime 创建时间（时间戳）
     * @return 0-5之间的推荐指数
     */
    public static double calculateRecommendation(RecommendWeightEnum weight, long createTime) {
        // 时间衰减因子：距离现在越近，值越大
        long currentTime = System.currentTimeMillis();
        double timeFactor = calculateTimeFactor(createTime, currentTime);

        // 计算原始推荐值
        double rawScore = weight.getValue() * WEIGHT_COEFFICIENT + timeFactor * TIME_COEFFICIENT;

        // 映射到0-5范围
        return normalizeToFivePoint(rawScore);
    }

    /**
     * 计算时间因子（0-10范围）
     */
    private static double calculateTimeFactor(long createTime, long currentTime) {
        // 计算时间差（小时）
        long diffHours = (currentTime - createTime) / (1000 * 60 * 60);

        // 时间衰减公式：e^(-衰减系数 * 时间差)
        double decayRate = 0.01; // 衰减系数，可调整
        double timeFactor = 10 * Math.exp(-decayRate * diffHours);

        return Math.max(0, Math.min(10, timeFactor));
    }

    /**
     * 将原始分数归一化到0-5范围
     */
    private static double normalizeToFivePoint(double rawScore) {
        // 理论最大值：HIGH(3) * 1.2 + 时间因子最大值(10) * 0.5 = 3.6 + 5 = 8.6
        // 理论最小值：LOW(1) * 1.2 + 时间因子最小值(0) * 0.5 = 1.2
        double minScore = 1.2;
        double maxScore = 8.6;

        // 线性映射到0-5
        double normalized = (rawScore - minScore) / (maxScore - minScore) * 5;

        return Math.max(0, Math.min(5, normalized));
    }
}
