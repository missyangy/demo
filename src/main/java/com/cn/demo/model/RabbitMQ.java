package com.cn.demo.model;

import com.rabbitmq.client.ConnectionFactory;
import lombok.Data;
import lombok.ToString;

/**
 * @program: demo
 * @description: RabbitMQ对象
 * @author: DongLianPo
 * @create: 2019/02/21 9:29
 **/
@Data
@ToString
public class    RabbitMQ {
    /**
     * 请求路径
     */
    private String path;

    /**
     * 请求端口
     */
    private Integer port;

    /**
     * 发送消息
     */
    private String message;

    /**
     * 请求用户名
     */
    private String userName;

    /**
     * 请求密码
     */
    private String passWorld;

    /**
     * 请求队列名称
     */
    private String queueName;

    /**
     * 请求的交换机名称
     */
    private String exchangeName;

    /**
     * 路由Key
     */
    private String routingKey;


    /**
     * ConnectionFactory配置相关地址
     *
     * @param rabbitMQ 数据对象
     * @return 返回连接
     */
    public static ConnectionFactory setRabbitForFactory(RabbitMQ rabbitMQ) {
        ConnectionFactory factory = new ConnectionFactory();
        if (rabbitMQ != null) {
            factory.setHost(rabbitMQ.getPath());
            factory.setPort(rabbitMQ.getPort());
            factory.setUsername(rabbitMQ.getUserName());
            factory.setPassword(rabbitMQ.getPassWorld());
        }
        return factory;
    }
}
