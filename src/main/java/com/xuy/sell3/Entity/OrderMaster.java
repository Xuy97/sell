package com.xuy.sell3.Entity;

import com.xuy.sell3.enums.OrderStatusEnum;
import com.xuy.sell3.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class OrderMaster {

    /**订单ID */
    @Id
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
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();

    /**支付状态 默认0未支付*/
    private Integer payStatus= PayStatusEnum.WAIT.getCode();

    /**创建时间 */
    private Date createTime;

    /**更新时间 */
    private Date updateTime;

}
