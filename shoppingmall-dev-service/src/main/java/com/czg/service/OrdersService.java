package com.czg.service;


import com.czg.pojo.OrderStatus;
import com.czg.pojo.bo.SubmitOrderBO;
import com.czg.pojo.vo.OrderVO;


/**
 * @author augenye
 * @date 2019/11/9 4:48 下午
 */
public interface OrdersService {

    /**
     * 创建订单相关信息
     *
     * @param submitOrderBO 订单
     */
    OrderVO createOrder(SubmitOrderBO submitOrderBO);

    /**
     * 修改订单状态
     * @param orderId orderId
     * @param orderStatus orderStatus
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 查询订单状态
     * @return
     */
    OrderStatus queryOrderStatusInfo(String orderId);

    /**
     * 关闭超时为支付订单
     */
    void closeOrder();

}
