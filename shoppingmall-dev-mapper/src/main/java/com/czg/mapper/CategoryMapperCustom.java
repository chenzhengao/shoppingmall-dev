package com.czg.mapper;

import com.czg.pojo.vo.CategoryVo;

import com.czg.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {

    /**
     * 查询一级分类下的子分类
     * @param rootCatId
     * @return
     */
    List<CategoryVo> getSubCatList(Integer rootCatId);

    /**
     * 查询每个类别商品的最新6个信息
     * @param map
     * @return
     */
    List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);
}