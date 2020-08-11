package com.czg.pojo.vo;

import com.czg.pojo.Items;
import com.czg.pojo.ItemsImg;
import com.czg.pojo.ItemsParam;
import com.czg.pojo.ItemsSpec;

import java.util.List;

/**
 * @program: shoppingmall-dev
 * @description: 商品信息展示
 * @author: czg
 * @create: 2020-08-01 10:13
 */
public class ItemInfoVO {
    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParams;

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public List<ItemsImg> getItemImgList() {
        return itemImgList;
    }

    public void setItemImgList(List<ItemsImg> itemImgList) {
        this.itemImgList = itemImgList;
    }

    public List<ItemsSpec> getItemSpecList() {
        return itemSpecList;
    }

    public void setItemSpecList(List<ItemsSpec> itemSpecList) {
        this.itemSpecList = itemSpecList;
    }

    public ItemsParam getItemParams() {
        return itemParams;
    }

    public void setItemParams(ItemsParam itemParams) {
        this.itemParams = itemParams;
    }
}
