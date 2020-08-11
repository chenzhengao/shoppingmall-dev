package com.czg.controller;

import com.czg.ennum.YesOrNo;
import com.czg.pojo.Carousel;
import com.czg.pojo.Category;
import com.czg.pojo.vo.CategoryVo;
import com.czg.pojo.vo.NewItemsVO;
import com.czg.service.CarouselService;
import com.czg.service.CategoryService;
import com.czg.utils.JSONResult;
import com.czg.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: shoppingmall-dev
 * @description: 首页相关接口
 * @author: czg
 * @create: 2020-07-30 17:38
 */
@Api(value = "首页", tags = "首页相关接口")
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表", httpMethod = "GET")
    @GetMapping("/carousel")
    public JSONResult carousel() {
        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);
        return JSONResult.ok(list);
    }

    /**
     * 首页分类展示需求：
     * 1. 第一次刷新主页查询大分类，渲染展示到首页
     * 2. 如果鼠标上移到大分类，则加载其子分类的内容，如果已经存在子分类，则不需要加载 lazy load
     */
    @ApiOperation(value = "获取商品(一级分类)", notes = "获取商品(一级分类)", httpMethod = "GET")
    @GetMapping("/cats")
    public JSONResult cats() {
        List<Category> list = categoryService.queryAllRootLevelCat();
        return JSONResult.ok(list);
    }

    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public JSONResult subCats(@ApiParam(name = "rootCatId", value = "一级分类ID", required = true)
                                  @PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return JSONResult.errorMsg("分类不存在");
        }
        List<CategoryVo> list = categoryService.getSubCatList(rootCatId);
        return JSONResult.ok(list);
    }

    @ApiOperation(value = "查询每个分类下六个最新商品", notes = "查询每个分类下六个最新商品", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public JSONResult sixNewItems(@ApiParam(name = "rootCatId", value = "一级分类ID", required = true) @PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return JSONResult.errorMsg("分类不存在");
        }
        List<NewItemsVO> list = categoryService.getSixNewItemsLazy(rootCatId);

        return JSONResult.ok(list);
    }


}
