package com.xuy.sell3.service;

import com.xuy.sell3.Entity.OrderMaster;
import com.xuy.sell3.dto.OrderDTO;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 订单
 */
public interface OrderService {
    /**创建订单 */
    OrderDTO create(OrderDTO orderDTO);

    /**查询单个订单 */
    OrderDTO findOne(String orderId);

    /**查询列表 */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**取消订单 */
    OrderDTO cancel(OrderDTO orderDTO);

    /**完结订单 */
    OrderDTO finish(OrderDTO orderDTO);

    /**支付订单 */
    OrderDTO paid(OrderDTO orderDTO);

    /**查询所有列表 */
    Page<OrderDTO> findList(Pageable pageable);

}
