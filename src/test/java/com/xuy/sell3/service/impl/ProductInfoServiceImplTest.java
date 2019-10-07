package com.xuy.sell3.service.impl;

import com.xuy.sell3.Entity.ProductInfo;
import com.xuy.sell3.enums.ProductStatusEnum;
import com.xuy.sell3.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoService infoService;

    @Test
    public void findOne() {

        ProductInfo productInfo = infoService.findOne("123456");
        Assert.assertEquals("123456", productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = infoService.findUpAll();
        Assert.assertNotEquals(0, upAll.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest=new PageRequest(0, 2);
        Page<ProductInfo> productInfoPage = infoService.findAll(pageRequest);
        System.out.println(productInfoPage);
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃的虾");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(2);
        ProductInfo info = infoService.save(productInfo);
        Assert.assertNotNull(info);
    }

    @Test
    public void onSale(){
        ProductInfo productInfo = infoService.onSale("123456");
        log.info("[productInfo]={}",productInfo);
        log.info("[ProductStatusEnum]={}",productInfo.getProductStatusEnum());
        Assert.assertEquals(ProductStatusEnum.UP, productInfo.getProductStatusEnum());
    }
}