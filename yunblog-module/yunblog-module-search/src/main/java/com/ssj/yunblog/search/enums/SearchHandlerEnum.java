package com.ssj.yunblog.search.enums;

/**
 * 搜索策略枚举类
 *
 * @author: yunkun
 * @Date: 2025/10/2
 */
public enum SearchHandlerEnum {

    ES_SEARCH("es", "1", "Elasticsearch作为底层搜索执行器"),

    MYSQL_SEARCH("mysql", "2", "Mysql作为底层搜索执行器");

    private final String handlerName;

    private final String handlerCode;

    private final String handlerDesc;


    SearchHandlerEnum(String name, String code, String desc) {
        this.handlerName = name;
        this.handlerCode = code;
        this.handlerDesc = desc;
    }

    public static SearchHandlerEnum getHandlerByName(String name) {
        for (SearchHandlerEnum handler : SearchHandlerEnum.values()) {
            if (handler.getHandlerName().equals(name)) {
                return handler;
            }
        }
        return null;
    }

    public static SearchHandlerEnum getHandlerByCode(String code) {
        for (SearchHandlerEnum handler : SearchHandlerEnum.values()) {
            if (handler.getHandlerCode().equals(code)) {
                return handler;
            }
        }
        return null;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public String getHandlerCode() {
        return handlerCode;
    }

    public String getHandlerDesc() {
        return handlerDesc;
    }
}
