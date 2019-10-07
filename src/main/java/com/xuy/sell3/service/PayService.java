package com.xuy.sell3.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.xuy.sell3.dto.OrderDTO;

/**
 * 支付
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    /**
     * 微信退款
     * @param orderDTO
     */
    RefundResponse refund(OrderDTO orderDTO);
}
