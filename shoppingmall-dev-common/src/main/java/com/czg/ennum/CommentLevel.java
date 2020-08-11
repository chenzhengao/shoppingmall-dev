package com.czg.ennum;

/**
 * @program: shoppingmall-dev
 * @description:
 * @author: czg
 * @create: 2020-08-01 10:58
 */
public enum CommentLevel {
    GOOD(1, "好评"),
    NORMAL(2, "中评"),
    BAD(3, "差评");

    public final Integer type;
    public final String value;


    CommentLevel(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
