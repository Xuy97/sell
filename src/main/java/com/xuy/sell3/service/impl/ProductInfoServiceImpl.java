package com.xuy.sell3.service.impl;

import com.xuy.sell3.Entity.ProductInfo;
import com.xuy.sell3.dto.CartDTO;
import com.xuy.sell3.enums.ProductStatusEnum;
import com.xuy.sell3.enums.ResultEnum;
import com.xuy.sell3.exception.SellException;
import com.xuy.sell3.repository.ProductInfoRepository;
import com.xuy.sell3.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.PrinterInfo;
import java.util.List;

/**
 * 商品
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository infoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return infoRepository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return infoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return infoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return infoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = infoRepository.findOne(cartDTO.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            infoRepository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = infoRepository.findOne(cartDTO.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result=productInfo.getProductStock()-cartDTO.getProductQuantity();

            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);

            infoRepository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = infoRepository.findOne(productId);
        if(productInfo==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum()==ProductStatusEnum.UP){
            throw new SellException(ResultEnum.product_status_error);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        ProductInfo save = infoRepository.save(productInfo);
        return save;
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = infoRepository.findOne(productId);
        if(productInfo==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum()==ProductStatusEnum.DOWN){
            throw new SellException(ResultEnum.product_status_error);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        ProductInfo save = infoRepository.save(productInfo);
        return save;
    }
}
