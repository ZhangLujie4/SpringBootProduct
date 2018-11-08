package com.zlj.product.service.impl;

import com.zlj.product.dataobject.ProductCategory;
import com.zlj.product.repository.ProductCategoryRepository;
import com.zlj.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tori
 * 2018/8/6 下午1:49
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }
}
