package com.xuy.sell3.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xuy.sell3.Entity.OrderDetail;
import com.xuy.sell3.enums.OrderStatusEnum;
import com.xuy.sell3.enums.PayStatusEnum;
import com.xuy.sell3.utils.EnumUtil;
import com.xuy.sell3.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单传输的
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    /**订单ID */
    private String orderId;

    /**买家名字 */
    private String buyerName;

    /**买家手机号 */
    private String buyerPhone;

    /**买家的微信openid */
    private String buyerOpenid;

    /**订单地址*/
    private String buyerAddress;

    /**订单金额 */
    private BigDecimal orderAmount;

    /**订单状态 默认0新订单 */
    private Integer orderStatus;

    /**支付状态 默认0未支付*/
    private Integer payStatus;

    /**创建时间 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**更新时间 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /**订单详情 */
    private List<OrderDetail> orderDetails;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
       return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
       return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
