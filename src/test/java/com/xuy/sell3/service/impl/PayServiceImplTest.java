package com.xuy.sell3.service.impl;

import com.xuy.sell3.dto.OrderDTO;
import com.xuy.sell3.service.OrderService;
import com.xuy.sell3.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO=orderService.findOne("1568951709094740033");
        payService.create(orderDTO);
    }
}