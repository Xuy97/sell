package com.xuy.sell3.controller;

import com.xuy.sell3.dto.OrderDTO;
import com.xuy.sell3.enums.ResultEnum;
import com.xuy.sell3.exception.SellException;
import com.xuy.sell3.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

import javax.jws.WebParam;
import java.util.Map;

/**
 * 卖家端
 */
@Slf4j
@Controller
@RequestMapping("/seller/order")
public class SellerOrdercontroller {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param page 第几页，从1页开始
     * @param size 一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page" ,defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request=new PageRequest(page-1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);

        return  new ModelAndView("order/list",map);
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        map.put("url", "/sell/seller/order/list");
        try {
            OrderDTO one = orderService.findOne(orderId);
            orderService.cancel(one);

        }catch (SellException e){
            log.error("[卖家 取消订单] 查询不到订单");

            map.put("msg", e.getMessage());

            return new ModelAndView("common/error",map);
        }

        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        return new ModelAndView("common/success",map);

    }

    /**
     * 订单详情
      * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO=new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e){
            log.error("[卖家 查询订单详情] 查询不到订单");

            map.put("url", "/sell/seller/order/list");
            map.put("msg", e.getMessage());

            return new ModelAndView("common/error",map);
        }
        map.put("orderDTO", orderDTO);
        return new ModelAndView("order/detail",map);
    }

    /**
     * 完成订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finished(@RequestParam("orderId") String orderId,
                                 Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("[卖家 完成订单] 完成订单异常");

            map.put("url", "/sell/seller/order/list");
            map.put("msg", e.getMessage());

            return new ModelAndView("common/error",map);
        }

        map.put("url", "/sell/seller/order/list");
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        return new ModelAndView("common/success",map);
    }
}
