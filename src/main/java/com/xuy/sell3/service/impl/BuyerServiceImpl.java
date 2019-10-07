package com.xuy.sell3.service.impl;

import com.xuy.sell3.dto.OrderDTO;
import com.xuy.sell3.enums.ResultEnum;
import com.xuy.sell3.exception.SellException;
import com.xuy.sell3.service.BuyerService;
import com.xuy.sell3.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO=checkOrderOwner(openid, orderId);
        if(orderDTO==null){
            log.error("[取消订单] 查询不到该订单, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO==null){
            return null;
        }
        if(orderDTO.getBuyerOpenid().equals(openid)){
            log.error("[订单查询] 订单的openid不一致, openid={}, orderDTO={}",openid,orderDTO);
            throw new SellException(ResultEnum.ORDER_PWNER_ERROR);
        }
        return orderDTO;
    }
}
