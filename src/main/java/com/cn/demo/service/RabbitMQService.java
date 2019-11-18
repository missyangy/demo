package com.cn.demo.service;

import com.cn.demo.model.RabbitMQ;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: demo
 * @description:
 * @author: DongLianPo
 * @create: 2019/02/21 9:27
 **/
public interface RabbitMQService {
    /**
     * 发送消息到队列
     *
     * @param rabbitMQ 数据对象
     */
    void sendMessageForQueue(RabbitMQ rabbitMQ);

    /**
     * 接收队列中电消息（简单模式一生产一消费）
     *
     * @param rabbitMQ 数据对象
     */
    void recvMessageForQueue(RabbitMQ rabbitMQ);

    /**
     * 发消息到工作队列
     *
     * @param rabbitMQ
     */
    void sendMessageForWorkQueue(RabbitMQ rabbitMQ);


    /**
     * 接收队列消息(工作模式，一生产多消费，每个消费者获取消息唯一)
     *
     * @param rabbitMQ
     * @throws Exception
     */
    void recvMessageForWorkQueue(RabbitMQ rabbitMQ) throws Exception;

    /**
     * 发送消息到Exchange(广播模式)
     *
     * @param rabbitMQ
     */
    void sendMessageForExchange(RabbitMQ rabbitMQ);

    /**
     * 从交换机中接收消息
     *
     * @param rabbitMQ 数据对象
     */
    void recvMessageForExchange(RabbitMQ rabbitMQ);


    /**
     * 发送消息到Exchange(路由模式)
     *
     * @param rabbitMQ 数据对象
     */
    void sendMessageForDirectExchange(RabbitMQ rabbitMQ);


    /**
     * 从交换机获取与交换机绑定的并且密钥一致的队列消息
     *
     * @param rabbitMQ 数据对象
     */
    void recvMessageForDirectExchange(RabbitMQ rabbitMQ);

    /**
     * 发送消息到TopicExchange(主题模式)
     *
     * @param rabbitMQ 数据对象
     */
    void sendMessageForTopicExchange(RabbitMQ rabbitMQ);

    /**
     * 从交换机获取相匹配的消息
     *
     * @param rabbitMQ 数据对象
     */
    void recvMessageForTopicExchange(RabbitMQ rabbitMQ);


}
