package com.czg.service;

import com.czg.pojo.Category;
import com.czg.pojo.vo.CategoryVo;
import com.czg.pojo.vo.NewItemsVO;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有大分类
     * @return
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类id查询子分类
     * @param rootCatId
     * @return
     */
    List<CategoryVo> getSubCatList(Integer rootCatId);

    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);

}
