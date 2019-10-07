package com.xuy.sell3.controller;

import com.xuy.sell3.Entity.ProductCategory;
import com.xuy.sell3.Entity.ProductInfo;
import com.xuy.sell3.VO.ProductInfoVO;
import com.xuy.sell3.VO.ProductVO;
import com.xuy.sell3.VO.ResultVO;
import com.xuy.sell3.service.CategoryService;
import com.xuy.sell3.service.ProductInfoService;
import com.xuy.sell3.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@Controller
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService infoService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @ResponseBody
    public ResultVO list(){

        //1.查询所有上架商品
        List<ProductInfo> upAll = infoService.findUpAll();

        //2.查询类目(一次性查询)
        List<Integer> categoryTypeList=upAll.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼装
        List<ProductVO> productVOS=new ArrayList<>();
        for(ProductCategory productCategory:productCategories){
            ProductVO productVO=new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOS=new ArrayList<>();
            for(ProductInfo productInfo:upAll){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            }
            productVO.setProductInfos(productInfoVOS);
            productVOS.add(productVO);
        }
        return  ResultVOUtils.success(productVOS);
    }
}
