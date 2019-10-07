package com.xuy.sell3.service;

import com.xuy.sell3.Entity.SellerInfo;

public interface SellerService {
    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
