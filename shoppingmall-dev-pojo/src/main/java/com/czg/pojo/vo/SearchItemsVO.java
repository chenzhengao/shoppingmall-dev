package com.czg.pojo.vo;


/**
 * 用于搜素商品列表的VO
 * @author augenye
 * @date 2019/11/8 10:47 上午
 */
public class SearchItemsVO {

    private String itemId;
    private String itemName;
    private Integer sellCounts;
    private String imgUrl;
    private int price;//分为单位

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getSellCounts() {
        return sellCounts;
    }

    public void setSellCounts(Integer sellCounts) {
        this.sellCounts = sellCounts;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

