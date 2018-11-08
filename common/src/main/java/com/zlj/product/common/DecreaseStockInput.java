package com.zlj.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tori
 * @description
 * @date 2018/9/4 下午4:28
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;
}
