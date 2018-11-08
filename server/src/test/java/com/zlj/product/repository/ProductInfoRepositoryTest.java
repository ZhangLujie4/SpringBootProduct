package com.zlj.product.repository;

import com.zlj.product.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author tori
 * 2018/8/6 下午12:29
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> result = repository.findByProductStatus(0);
        Assert.assertTrue(result.size() > 0);
     }
}