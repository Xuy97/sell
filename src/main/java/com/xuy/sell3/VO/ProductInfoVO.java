package com.xuy.sell3.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 返回给前端
 * 商品信息
 */
@Data
public class ProductInfoVO {

    @JsonProperty("id")
    private String productId;

    /**名字 */
    @JsonProperty("name")
    private String productName;

    /**描述 */
   @JsonProperty("description")
    private String productDescription;

    /**单价 */
    @JsonProperty("price")
    private BigDecimal productPrice;

    /**小图 */
    @JsonProperty("icon")
    private String productIcon;

}
