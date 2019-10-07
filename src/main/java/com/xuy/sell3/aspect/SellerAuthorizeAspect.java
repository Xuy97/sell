package com.xuy.sell3.aspect;

import com.xuy.sell3.constant.CookieConstant;
import com.xuy.sell3.constant.RedisConstant;
import com.xuy.sell3.exception.SellException;
import com.xuy.sell3.exception.SellerAuthorizeException;
import com.xuy.sell3.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.attachment.AttachmentMarshaller;

/**
 * 面向切面
 */
@Slf4j
@Aspect
@Component
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.xuy.sell3.controller.Seller*.*(..))"+
    "&& !execution(public * com.xuy.sell3.controller.SellerUserController.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();
        //1.查询cookie
        Cookie cookie= CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie==null){
            log.warn("[登入校验] Cookie查不到token");
            throw new SellerAuthorizeException();
        }
        //redis里查询
        String tokenValue = redisTemplate.opsForValue()
                .get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            log.warn("[登入校验] Redis中查找不到token");
            throw new SellerAuthorizeException();
        }

    }
}
