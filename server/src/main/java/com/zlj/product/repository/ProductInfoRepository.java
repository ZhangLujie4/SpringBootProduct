package com.zlj.product.repository;

import com.zlj.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author tori
 * 2018/8/6 下午12:26
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer status);

    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
