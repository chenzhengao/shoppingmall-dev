package com.czg.controller;


import com.czg.ennum.OrderStatusEnum;
import com.czg.ennum.PayMethod;
import com.czg.pojo.OrderStatus;
import com.czg.pojo.bo.SubmitOrderBO;
import com.czg.pojo.vo.MerchantOrdersVO;
import com.czg.pojo.vo.OrderVO;
import com.czg.service.OrdersService;
import com.czg.utils.CookieUtils;
import com.czg.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "订单相关", tags = {"订单相关的api接口"})
@RequestMapping("orders")
@RestController
public class OrdersController extends BaseController {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "创建订单", notes = "创建订单")
    @PostMapping("/create")
    public JSONResult create(@RequestBody SubmitOrderBO submitOrderBO, HttpServletRequest request, HttpServletResponse response) {

        if (submitOrderBO.getPayMethod() != PayMethod.WEIXIN.type && submitOrderBO.getPayMethod() != PayMethod.ALIPAY.type) {
            return JSONResult.errorMsg("请选择支付方式");
        }

        // log.info("order info: {}", submitOrderBO.toString());

        // 1. 创建订单
        OrderVO orderVO = ordersService.createOrder(submitOrderBO);
        String orderId = orderVO.getOrderId();


        // 2. 创建订单以后，移除购物车中已结算或提交的商品

        //TODO 整合redis之后，完善购物车中的以结算商品清除，并且同步到前端的cookie
        CookieUtils.setCookie(request, response, FOODIE_SHOPCART, "", true);

        // 3. 支付中心发送订单，保存支付中心的订单数据
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(payReturnUrl);
        //方便测试购买，统一改为一分钱
        merchantOrdersVO.setAmount(1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //支付中心账号登录账号密码，有后端直接发起请求
        headers.add("imoocUserId", "6544218-1677390657");
        headers.add("password", "919f-lapk-fzm1-gd0o");

        HttpEntity<MerchantOrdersVO> entity = new HttpEntity<>(merchantOrdersVO, headers);
        //paymentUrl 支付中心调用地址
        ResponseEntity<JSONResult> responseEntity = restTemplate.postForEntity(paymentUrl, entity, JSONResult.class);
        //请求结果
        JSONResult paymentResult = responseEntity.getBody();
        if (paymentResult.getStatus() != 200) {
            return JSONResult.errorMsg("支付中心订单创建失败");
        }

        return JSONResult.ok(orderId);
    }

    @PostMapping("/notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId) {
        ordersService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }

    @PostMapping("/getPaidOrderInfo")
    public JSONResult getPaidOrderInfo(String orderId) {
        OrderStatus orderStatus = ordersService.queryOrderStatusInfo(orderId);
        return JSONResult.ok(orderStatus);
    }
}
