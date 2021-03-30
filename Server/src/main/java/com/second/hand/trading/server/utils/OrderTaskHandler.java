package com.second.hand.trading.server.utils;

import com.second.hand.trading.server.service.OrderService;

import java.util.concurrent.DelayQueue;

/**
 * 需要考虑的点：
 * 时效性（准时上架、不能提前自动取消，可以晚几毫秒，支付方需要判断是否到时间）
 * 高性能（不能给数据库太大压力，不能有太多的cpu空转）
 * 节约空间（内存有限）
 * 高可靠（不会有漏的订单）
 *
 * 方案1
 * 订单创建时间加索引
 * 定时一分钟执行一次SQL批量处理超时订单，子查询找出到期的订单id，update闲置状态和订单的状态。订单过期使用创建时间判断（多机器时存在时间同步问题），不用状态判断，所以只用update闲置的状态。
 * 优点：实现简单
 * 缺点：缺点时效性差，接近超时的订单需要等下次扫描（延迟<扫库时间间隔），未支付的订单实际会很少，一次扫库往往是白费的，无用功较多。
 * 扫库间隔太长时效性差，扫库时间间隔太短，数据库压力大
 *
 * 方案2
 * 全部订单存在jdk延时队列，启动时也要扫一次库，多线程（消费者）更新闲置状态
 * 线程池+阻塞队列
 * 死循环+延时队列
 * 优点：时效性高，（延迟<线程池的处理时间）
 * 缺点：浪费内存，有很多不用自动取消的订单，同时过期的订单太多时有大量的更新sql执行
 *
 * 方案3：
 * 5分钟定期扫库取出6分钟范围（可能已经过期，比如重启时）的订单（最后6分钟不支付的往往就是不买的了），放入jdk延时队列；死循环出队判断；出队时无可取消的订单就睡眠10毫秒，否则放入线程池（消费者）更新闲置状态，继续出队
 * 优点：节约内存，过滤了已支付的订单，只会有6分钟范围的少量订单查出来；
 * 时效性高（延迟<线程池的处理时间+睡眠时间）；
 * cpu空转少（有睡眠策略）；
 * 解耦，与其他服务完全分离，不需要为该功能专门做设计（比如订单入队）；
 * 缺点：实现复杂
 *
 * 方案4
 * 消息队列？Redis有序集合？
 * 分布式场景时可用MQ和Redis有序集合代替jdk延时队列，然后与方案3相同；
 * 没必要将全部订单都放中间件；
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
                        if(orderService.updateOrder(orderTask.getOrderModel())){
                            System.out.println("成功取消订单："+orderTask.getOrderModel());
                        }else {
                            System.out.println("取消任务："+orderTask.getOrderModel());
                        }

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
