package com.zlj.product.impl;

import com.zlj.product.DTO.CartDTO;
import com.zlj.product.ProductApplicationTests;
import com.zlj.product.common.DecreaseStockInput;
import com.zlj.product.dataobject.ProductInfo;
import com.zlj.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author tori
 * 2018/8/6 下午1:00
 */
@Component
public class ProductServiceImplTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findAllUp() {

        List<ProductInfo> result = productService.findAllUp();
        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void decreaseStock() throws Exception {
        DecreaseStockInput cartDTO = new DecreaseStockInput("157875196366160022", 2);
        productService.decreaseStock(Arrays.asList(cartDTO));
    }
}