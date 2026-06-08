package com.ssj.yunblog.common.enums;

import lombok.Getter;

/**
 * 留言类别枚举
 *
 * @author: yunkun
 * @Date: 2026/5/24
 */
@Getter
public enum MessageBoardEnum {

    ALL("all", "全部分类"),

    JOB("job", "求职避坑"),

    TECH("tech", "技术分享"),

    LIFE("life", "生活娱乐");

    private String code;

    private String desc;

    MessageBoardEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static MessageBoardEnum getByCode(String code) {
        for (MessageBoardEnum messageBoardEnum : MessageBoardEnum.values()) {
            if (messageBoardEnum.code.equals(code)) {
                return messageBoardEnum;
            }
        }
        return null;
    }
}
