package com.zlj.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tori
 * @description
 * @date 2018/9/4 下午4:29
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductInfoOutput {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;

    private Integer categoryType;
}
