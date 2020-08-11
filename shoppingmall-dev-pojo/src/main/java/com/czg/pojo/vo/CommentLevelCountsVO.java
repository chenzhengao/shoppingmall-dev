package com.czg.pojo.vo;

/**
 * @program: shoppingmall-dev
 * @description: 用于展示评价数量的VO
 * @author: czg
 * @create: 2020-08-01 10:52
 */
public class CommentLevelCountsVO {
    private Integer totalCounts;
    private Integer goodCounts;
    private Integer normalCounts;
    private Integer badCounts;

    public CommentLevelCountsVO(Integer totalCounts, Integer goodCounts, Integer normalCounts, Integer badCounts) {
        this.totalCounts = totalCounts;
        this.goodCounts = goodCounts;
        this.normalCounts = normalCounts;
        this.badCounts = badCounts;
    }

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
    }

    public Integer getGoodCounts() {
        return goodCounts;
    }

    public void setGoodCounts(Integer goodCounts) {
        this.goodCounts = goodCounts;
    }

    public Integer getNormalCounts() {
        return normalCounts;
    }

    public void setNormalCounts(Integer normalCounts) {
        this.normalCounts = normalCounts;
    }

    public Integer getBadCounts() {
        return badCounts;
    }

    public void setBadCounts(Integer badCounts) {
        this.badCounts = badCounts;
    }
}
