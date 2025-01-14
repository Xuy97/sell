package com.xuy.sell3.service;

import com.xuy.sell3.dto.OrderDTO;

/**
 * 消息推送
 */
public interface PushMessage {
    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
