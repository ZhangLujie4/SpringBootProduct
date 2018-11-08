package com.zlj.product.service.impl;

import com.zlj.product.common.DecreaseStockInput;
import com.zlj.product.common.ProductInfoOutput;
import com.zlj.product.dataobject.ProductInfo;
import com.zlj.product.enums.ProductStatusEnum;
import com.zlj.product.enums.ResultEnum;
import com.zlj.product.exception.ProductException;
import com.zlj.product.repository.ProductInfoRepository;
import com.zlj.product.service.ProductService;
import com.zlj.product.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author tori
 * 2018/8/6 下午12:55
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findAllUp() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return repository.findByProductIdIn(productIdList).stream()
                .map(e -> {
                    ProductInfoOutput productInfoOutput = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, productInfoOutput);
                    return productInfoOutput;
                }).collect(Collectors.toList());
    }

//    @Transactional
//    @Override
//    public void decreaseStock(List<DecreaseStockInput> cartDTOList) {
//
//        for (DecreaseStockInput cartDTO : cartDTOList) {
//            Optional<ProductInfo> productInfo = repository.findById(cartDTO.getProductId());
//            //判断商品是否存在
//            if (!productInfo.isPresent()) {
//                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
//            }
//
//            ProductInfo productInfo1 = productInfo.get();
//            //库存是否足够
//            Integer result = productInfo1.getProductStock()  - cartDTO.getProductQuantity();
//            if (result < 0) {
//                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
//            }
//
//            productInfo1.setProductStock(result);
//            repository.save(productInfo1);
//
//            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
//            BeanUtils.copyProperties(productInfo1, productInfoOutput);
//            //发送mq消息
//            amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutput));
//        }
//    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> cartDTOList) {

        List<ProductInfo> productInfoList = decreaseStockProcess(cartDTOList);

        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());
        //发送mq消息
        log.info(JsonUtil.toJson(productInfoOutputList));
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));
    }

    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> cartDTOList) {

        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfo = repository.findById(cartDTO.getProductId());
            //判断商品是否存在
            if (!productInfo.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo1 = productInfo.get();
            //库存是否足够
            Integer result = productInfo1.getProductStock()  - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo1.setProductStock(result);
            repository.save(productInfo1);

            productInfoList.add(productInfo1);
        }
        return productInfoList;
    }
}
