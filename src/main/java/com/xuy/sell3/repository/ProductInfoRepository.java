package com.xuy.sell3.repository;

import com.xuy.sell3.Entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    /**
     * 查询上架产品
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
