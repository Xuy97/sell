package com.xuy.sell3.utils;

import com.sun.org.apache.xpath.internal.operations.Equals;

public class MathUtil {

    private static final Double MONEY_RANGE=0.01;
    /**
     * 比较金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static boolean equals(Double d1,Double d2){
        Double abs = Math.abs(d1 - d2);
        if(abs<MONEY_RANGE){
            return true;
        }else {
            return false;
        }
    }
}
