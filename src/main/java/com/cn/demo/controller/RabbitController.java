package com.cn.demo.controller;

import com.cn.demo.model.RabbitMQ;
import com.cn.demo.service.RabbitMQService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description: Rabbit接口
 * @author: DongLianPo
 * @create: 2019/02/19 13:27
 **/
@RestController
@RequestMapping("/rabbit")
public class RabbitController {
    @Autowired
    AmqpTemplate amqpTemplate;
    @Autowired
    private Environment environment;
    @Autowired
    private RabbitMQService rabbitMQService;

    @RequestMapping("/send")
    public void send(RabbitMQ rabbitMQ) {
        rabbitMQService.sendMessageForQueue(rabbitMQ);
    }

    @RequestMapping("/recv")
    public void receiving(RabbitMQ rabbitMQ) {
        rabbitMQService.recvMessageForQueue(rabbitMQ);
    }

    @RequestMapping("/sendWork")
    public void senWork(RabbitMQ rabbitMQ) {
        rabbitMQService.sendMessageForWorkQueue(rabbitMQ);
    }

    @RequestMapping("/recvWork")
    public void workReceiving(RabbitMQ rabbitMQ) {
        try {
            rabbitMQService.recvMessageForWorkQueue(rabbitMQ);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/sendExchange")
    public void sendExchange(RabbitMQ rabbitMQ) {
        rabbitMQService.sendMessageForExchange(rabbitMQ);
    }

    @RequestMapping("/recvExchange")
    public void recvExchange(RabbitMQ rabbitMQ) {
        try {
            rabbitMQService.recvMessageForExchange(rabbitMQ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/sendDirectExchange")
    public void sendMessageForDirectExchange(RabbitMQ rabbitMQ) {
        rabbitMQService.sendMessageForDirectExchange(rabbitMQ);
    }

    @RequestMapping("/recvDirectExchange")
    public void recvExchangeDirectExchange(RabbitMQ rabbitMQ) {
        try {
            rabbitMQService.recvMessageForDirectExchange(rabbitMQ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/sendTopicExchange")
    public void sendMessageForTopicExchange(RabbitMQ rabbitMQ) {
        rabbitMQService.sendMessageForTopicExchange(rabbitMQ);
    }

    @RequestMapping("/recvTopicExchange")
    public void recvExchangeTopicExchange(RabbitMQ rabbitMQ) {
        try {
            rabbitMQService.recvMessageForTopicExchange(rabbitMQ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
