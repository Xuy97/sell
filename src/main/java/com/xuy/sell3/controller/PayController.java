package com.xuy.sell3.controller;

import com.lly835.bestpay.model.PayResponse;
import com.xuy.sell3.dto.OrderDTO;
import com.xuy.sell3.enums.ResultEnum;
import com.xuy.sell3.exception.SellException;
import com.xuy.sell3.service.OrderService;
import com.xuy.sell3.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 支付
 */
@Controller
@RequestMapping("pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String,Object> map){
        //1. 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2.发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);

        return new ModelAndView("pay/create",map);
    }

    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);

        //返回给微信处理结果
        return new ModelAndView("pay/success");
   }



}
