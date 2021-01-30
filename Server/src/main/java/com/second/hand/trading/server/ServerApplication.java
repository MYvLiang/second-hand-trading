package com.second.hand.trading.server;

import com.second.hand.trading.server.utils.OrderTaskHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 安全问题：缺少密码加密传输和存储，缺少userid的cookie加密
 * 未做提交限制
 * 部分接口未做幂等
 */
@SpringBootApplication()
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        OrderTaskHandler.run();
    }

}
