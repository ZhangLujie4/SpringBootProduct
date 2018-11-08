package com.zlj.product.enums;

import lombok.Getter;

/**
 * @author tori
 * @description
 * @date 2018/9/4 下午1:37
 */

@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(1, "商品不存在"),

    PRODUCT_STOCK_ERROR(2, "库存有误"),
    ;
    private String message;

    private Integer code;

    ResultEnum(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
