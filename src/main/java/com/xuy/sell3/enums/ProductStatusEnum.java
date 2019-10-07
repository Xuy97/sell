package com.xuy.sell3.enums;

import lombok.Getter;
import org.aopalliance.reflect.Code;

/**
 * 商品状态
 * 0上架，1下架
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
