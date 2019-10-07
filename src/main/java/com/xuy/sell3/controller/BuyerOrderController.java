package com.xuy.sell3.controller;

import com.xuy.sell3.VO.ResultVO;
import com.xuy.sell3.converter.OrderFrom2OrderDTOConverter;
import com.xuy.sell3.dto.OrderDTO;
import com.xuy.sell3.enums.ResultEnum;
import com.xuy.sell3.exception.SellException;
import com.xuy.sell3.from.OrderFrom;
import com.xuy.sell3.service.BuyerService;
import com.xuy.sell3.service.OrderService;
import com.xuy.sell3.utils.ResultVOUtils;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderFrom orderFrom,
                                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("[创建订单] 参数不正确 orderFrom={}" , orderFrom);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderFrom2OrderDTOConverter.convert(orderFrom);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetails())){
            log.error("[创建订单] 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO orderDTOReturn = orderService.create(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId", orderDTOReturn.getOrderId());

        return ResultVOUtils.success(map);
    }
    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value="page",defaultValue = "0") Integer page,
                                         @RequestParam(value="size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("[查询订单列表] openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDTO> list = orderService.findList(openid, pageRequest);

        return ResultVOUtils.success(list.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtils.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel (@RequestParam("openid") String openid,
                            @RequestParam("orderId") String orderId){
        buyerService.findOrderOne(openid, orderId);
        return ResultVOUtils.success();
    }


}
