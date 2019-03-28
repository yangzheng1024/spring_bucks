package com.geekbang.enums;

/**
 * 订单状态枚举类
 *
 * @author 杨正
 */
public enum OrderEnum {
    /**
     * 新建订单
     */
    INIT,

    /**
     * 已付款
     */
    PAID,

    /**
     * 酿造
     */
    BREWING,

    /**
     * 拿
     */
    TAKEN,

    /**
     * 取消
     */
    CANCELLED;
}
