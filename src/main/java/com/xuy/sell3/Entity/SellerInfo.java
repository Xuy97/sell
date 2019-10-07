package com.xuy.sell3.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 卖家信息
 */
@Data
@Entity
public class SellerInfo {

    @Id
    private String sellerId;

    private String userName;

    private String password;

    private String openid;
}
