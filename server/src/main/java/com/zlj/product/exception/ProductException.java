package com.zlj.product.exception;

import com.zlj.product.enums.ResultEnum;

/**
 * @author tori
 * @description
 * @date 2018/9/4 下午1:35
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
