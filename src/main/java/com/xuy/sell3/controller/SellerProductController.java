package com.xuy.sell3.controller;

import com.xuy.sell3.Entity.ProductCategory;
import com.xuy.sell3.Entity.ProductInfo;
import com.xuy.sell3.dto.OrderDTO;
import com.xuy.sell3.enums.ResultEnum;
import com.xuy.sell3.exception.SellException;
import com.xuy.sell3.from.ProductForm;
import com.xuy.sell3.service.CategoryService;
import com.xuy.sell3.service.ProductInfoService;
import com.xuy.sell3.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.persistence.criteria.From;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 */
@Slf4j
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 商品列表
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page" ,defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request=new PageRequest(page-1, size);
        Page<ProductInfo> productInfoPage= productInfoService.findAll(request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);

        return  new ModelAndView("product/list",map);
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try {
            productInfoService.onSale(productId);
        }catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
//        map.put("msg", ResultEnum.)
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try {
           productInfoService.offSale(productId);
        }catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
//        map.put("msg", ResultEnum.)
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                      Map<String,Object> map){
        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productInfoService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("product/index",map);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空,说明是新增
            if (!StringUtils.isEmpty(form.getProductId())){
             productInfo = productInfoService.findOne(form.getProductId());
            }else {
                form.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(form, productInfo);
            productInfoService.save(productInfo);
        }catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
}
