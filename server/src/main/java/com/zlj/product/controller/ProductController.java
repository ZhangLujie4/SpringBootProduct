package com.zlj.product.controller;

import com.zlj.product.DTO.CartDTO;
import com.zlj.product.VO.ProductInfoVO;
import com.zlj.product.VO.ProductVO;
import com.zlj.product.VO.ResultVO;
import com.zlj.product.common.DecreaseStockInput;
import com.zlj.product.common.ProductInfoOutput;
import com.zlj.product.dataobject.ProductCategory;
import com.zlj.product.dataobject.ProductInfo;
import com.zlj.product.service.CategoryService;
import com.zlj.product.service.ProductService;
import com.zlj.product.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tori
 * 2018/8/6 上午11:48
 */

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    /**
     * 1.查询所有在架商品
     * 2.获取类目type表
     * 3.查询类目
     * 4.构造数据
     * crossOrigin可以设置具体的ip在origins里面设置,allowCredentials为true时可以传cookies
     */
    @GetMapping("/list")
    //@CrossOrigin(allowCredentials = "true")
    public ResultVO<ProductVO> list() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<ProductInfo> productInfos = productService.findAllUp();
        List<Integer> categoryTypeList = productInfos.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        //log.info("categoryTypeList = {}", categoryTypeList);

        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfos) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }

    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productService.findList(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList) {
        productService.decreaseStock(cartDTOList);
    }
}
