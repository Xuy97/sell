package com.xuy.sell3.utils;

import com.xuy.sell3.VO.ResultVO;

public class ResultVOUtils {
    /**
     * 成功返回
     * @param obj
     * @return
     */
    public static ResultVO success(Object obj){
        ResultVO resultVO=new ResultVO();
        resultVO.setData(obj);
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        return resultVO;
    }

    /**
     * 成功空返回
     * @return
     */
    public static ResultVO success(){
        return null;
    }

    /**
     * 失败返回
     * @param code
     * @param msg
     * @return
     */
    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(msg);
        return resultVO;
    }
}
