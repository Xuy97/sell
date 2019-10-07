package com.xuy.sell3.repository;

import com.xuy.sell3.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    /**
     * 根据订单号
     * 查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrOrderId(String orderId);
}
