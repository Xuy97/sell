package com.xuy.sell3.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 */
@Data
public class ResultVO<T> {

    /**错误码 */
    private Integer code;

    /**返回消息 */
    private String message;

    /**具体内容 */
    private T data;
}
