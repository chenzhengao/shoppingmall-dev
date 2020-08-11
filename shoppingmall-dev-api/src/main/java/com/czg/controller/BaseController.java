package com.czg.controller;

import org.springframework.stereotype.Controller;

/**
 * @program: shoppingmall-dev
 * @description: 定义一些公共控制器资源
 * @author: czg
 * @create: 2020-08-01 12:40
 */
@Controller
public class BaseController {
    //默认评价页分页的大小为10
    public static final Integer COMMENT_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;
    //购物车cookie name
    public static final String FOODIE_SHOPCART = "shopcart";
    //微信支付中心 -> 支付中心 -> 天天吃货平台
    //                   ｜回调中心的Url
    String payReturnUrl = "http://bnxqj6.natappfree.cc/orders/notifyMerchantOrderPaid";

    // 支付中心的地址
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";
}
