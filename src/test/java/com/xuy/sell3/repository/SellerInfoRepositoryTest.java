package com.xuy.sell3.repository;

import com.xuy.sell3.Entity.SellerInfo;
import com.xuy.sell3.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUserName("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");

        SellerInfo save = sellerInfoRepository.save(sellerInfo);
        Assert.assertNotNull(save);
    }

    @Test
    public void testfindByOpenid(){
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("abc");
       Assert.assertEquals("abc", sellerInfo.getOpenid());
    }
}