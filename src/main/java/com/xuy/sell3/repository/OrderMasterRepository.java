package com.xuy.sell3.repository;

import com.xuy.sell3.Entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商品订单
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    /**
     * 根据买家openid
     * 查询订单
     * @param BuyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String BuyerOpenid, Pageable pageable);
}
