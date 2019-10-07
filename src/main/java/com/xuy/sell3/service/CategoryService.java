package com.xuy.sell3.service;

import com.xuy.sell3.Entity.ProductCategory;

import java.util.List;

/**
 * 类目
 */
public interface CategoryService {
    /**
     * 查询一条类目信息
     * @param cateoryId
     * @return
     */
    ProductCategory findOne(Integer cateoryId);

    /**
     * 查询所有类目信息
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 根据类目类型查询类目的信息
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 新增/更新
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
