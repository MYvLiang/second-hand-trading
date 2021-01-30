package com.second.hand.trading.server.utils;

import com.second.hand.trading.server.dao.IdleItemDao;
import com.second.hand.trading.server.dao.OrderDao;
import com.second.hand.trading.server.service.OrderService;

import javax.annotation.Resource;
import java.util.concurrent.DelayQueue;

/**
 * @author myl
 * @create 2021-01-28  0:06
 */
public class OrderTaskHandler {

    public static OrderService orderService=null;

    private static DelayQueue<OrderTask> delayQueue = new DelayQueue<>();

    public static void run(){
        new Thread(() -> {
            System.out.println("OrderTaskHandler run!");
            while (true) {
                if(orderService!=null&&delayQueue.size() >0){
                    OrderTask orderTask = delayQueue.poll();
                    if (orderTask != null) {
                        orderService.updateOrder(orderTask.getOrderModel());
                        System.out.println("成功取消："+orderTask.getOrderModel());
                    }
                }
            }
        }).start();

    }

    public static void addOrder(OrderTask o){
        System.out.println("添加任务："+o);
        delayQueue.put(o);
    }
}
