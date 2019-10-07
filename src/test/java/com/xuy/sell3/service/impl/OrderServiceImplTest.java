package com.xuy.sell3.service.impl;

import com.xuy.sell3.Entity.OrderDetail;
import com.xuy.sell3.dto.OrderDTO;
import com.xuy.sell3.enums.OrderStatusEnum;
import com.xuy.sell3.enums.PayStatusEnum;
import com.xuy.sell3.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    private final String BUYEROPENID="110110";
    private final String ORDER_ID="1568897324238308805";


    @Test
    public void create() {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("廖师兄");
        orderDTO.setBuyerAddress("慕课网");
        orderDTO.setBuyerPhone("15223658746");
        orderDTO.setBuyerOpenid(BUYEROPENID);

        //购物车
        List<OrderDetail> orderDetails=new ArrayList<>();

        OrderDetail o1=new OrderDetail();
        o1.setProductId("12345687");
        o1.setProductQuantity(1);
        orderDetails.add(o1);

        OrderDetail o2=new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(1);
        orderDetails.add(o2);
        orderDTO.setOrderDetails(orderDetails);
        OrderDTO orderDTO1 = orderService.create(orderDTO);
        log.info("[创建订单] result={}", orderDTO1);

    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        log.info("[查询单个订单] orderDTO={}", orderDTO);
        Assert.assertEquals(ORDER_ID, orderDTO.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest request=new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYEROPENID, request);
        Assert.assertEquals(3, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO cancel = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), cancel.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO cancel = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), cancel.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO cancel = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), cancel.getPayStatus());
    }

    @Test
    public void findAll(){
        PageRequest pageRequest=new PageRequest(0, 2);
        Page<OrderDTO> list = orderService.findList(pageRequest);
        Assert.assertNotEquals(0, list.getTotalElements());
    }
}