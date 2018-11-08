package com.zlj.product.enums;

import lombok.Getter;

/**
 * @author tori
 * 2018/8/6 下午12:57
 */

@Getter
public enum ProductStatusEnum {

    UP(0, "在架"),

    DOWN(1, "下架")
    ;

    private Integer code;

    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
