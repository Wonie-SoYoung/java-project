package com.soyoung.dobbu;

import com.soyoung.dobbu.service.OrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("consumer.xml");
        OrderService orderService = ioc.getBean(OrderService.class);
        orderService.initOrder("1");
        System.in.read();
    }
}
