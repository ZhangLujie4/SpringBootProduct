package com.zlj.product.service;

import com.zlj.product.dataobject.ProductCategory;

import java.util.List;

/**
 * @author tori
 * 2018/8/6 下午1:47
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
