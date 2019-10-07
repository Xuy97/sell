package com.xuy.sell3.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xuy.sell3.Entity.OrderDetail;
import com.xuy.sell3.dto.OrderDTO;
import com.xuy.sell3.enums.ResultEnum;
import com.xuy.sell3.exception.SellException;
import com.xuy.sell3.from.OrderFrom;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderFrom2OrderDTOConverter {

    public static OrderDTO convert(OrderFrom orderFrom){
        Gson gson=new Gson();
        OrderDTO orderDTO=new OrderDTO();

        List<OrderDetail> orderDetails=new ArrayList<>();
        try {
            orderDetails=gson.fromJson(orderFrom.getItems(),
                    new TypeToken<List<OrderDetail>>(){
                    }.getType());
        }catch (Exception e){
            log.error("[对象转换] 错误, string={}",orderFrom.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setBuyerName(orderFrom.getName());
        orderDTO.setBuyerPhone(orderFrom.getPhone());
        orderDTO.setBuyerAddress(orderFrom.getAddress());
        orderDTO.setBuyerOpenid(orderFrom.getOpenid());
        orderDTO.setOrderDetails(orderDetails);

        return orderDTO;
    }
}
