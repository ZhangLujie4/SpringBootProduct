package com.zlj.product.service;

import com.zlj.product.DTO.CartDTO;
import com.zlj.product.common.DecreaseStockInput;
import com.zlj.product.common.ProductInfoOutput;
import com.zlj.product.dataobject.ProductInfo;

import java.util.List;

/**
 * @author tori
 * 2018/8/6 下午12:53
 */
public interface ProductService {

    List<ProductInfo> findAllUp();

    List<ProductInfoOutput> findList(List<String> productIdList);

    void decreaseStock(List<DecreaseStockInput> cartDTOList);
}
