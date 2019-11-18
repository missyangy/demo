package com.cn.demo.service.impl;

import com.cn.demo.model.RabbitMQ;
import com.cn.demo.service.RabbitMQService;
import com.rabbitmq.client.*;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @program: demo
 * @description: RabbitMQ相关实现
 * @author: DongLianPo
 * @create: 2019/02/21 9:33
 **/
@Service
public class RabbitMQServiceImpl implements RabbitMQService {

    @Override
    public void sendMessageForQueue(RabbitMQ rabbitMQ) {
        ConnectionFactory factory = RabbitMQ.setRabbitForFactory(rabbitMQ);
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(rabbitMQ.getQueueName(), false, false, false, null);
            channel.basicPublish("", rabbitMQ.getQueueName(), null, rabbitMQ.getMessage().getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] SentMessageForQueue '" + rabbitMQ.getMessage() + "'");
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void recvMessageForQueue(RabbitMQ rabbitMQ) {
        ConnectionFactory factory = RabbitMQ.setRabbitForFactory(rabbitMQ);
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(rabbitMQ.getQueueName(), false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] ReceivedMessageForQueue '" + message + "'");
            };
            channel.basicConsume(rabbitMQ.getQueueName(), true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    @Override
    public void sendMessageForWorkQueue(RabbitMQ rabbitMQ) {
        ConnectionFactory factory = RabbitMQ.setRabbitForFactory(rabbitMQ);
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(rabbitMQ.getQueueName(), true, false, false, null);
            channel.basicPublish("", rabbitMQ.getQueueName(), MessageProperties.PERSISTENT_TEXT_PLAIN, rabbitMQ.getMessage().getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] SentMessageForWorkQueue '" + rabbitMQ.getMessage() + "'");
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    @Override
    public void recvMessageForWorkQueue(RabbitMQ rabbitMQ) throws Exception {
        ConnectionFactory factory = RabbitMQ.setRabbitForFactory(rabbitMQ);

        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(rabbitMQ.getQueueName(), true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        channel.basicQos(1);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] ReceivedMessageForWorkQueue '" + message + "'");
            try {
                doWork(message);
            } finally {
                System.out.println(" [x] Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        channel.basicConsume(rabbitMQ.getQueueName(), false, deliverCallback, consumerTag -> {
        });
    }

    @Override
    public void sendMessageForExchange(RabbitMQ rabbitMQ) {
        ConnectionFactory factory = RabbitMQ.setRabbitForFactory(rabbitMQ);
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(rabbitMQ.getExchangeName(), BuiltinExchangeType.FANOUT);
            channel.basicPublish(rabbitMQ.getExchangeName(), "", null, rabbitMQ.getMessage().getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] SentMessageForExchange '" + rabbitMQ.getMessage() + "'");
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void recvMessageForExchange(RabbitMQ rabbitMQ) {
        ConnectionFactory factory = RabbitMQ.setRabbitForFactory(rabbitMQ);
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(rabbitMQ.getExchangeName(), BuiltinExchangeType.FANOUT);
            channel.queueDeclare(rabbitMQ.getQueueName(), true, false, false, null);
            channel.queueBind(rabbitMQ.getQueueName(), rabbitMQ.getExchangeName(), "");
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] ReceivedMessageForExchange '" + message + "'");
            };
            channel.basicConsume(rabbitMQ.getQueueName(), true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    @Override
    public void sendMessageForDirectExchange(RabbitMQ rabbitMQ) {
        ConnectionFactory factory = RabbitMQ.setRabbitForFactory(rabbitMQ);
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(rabbitMQ.getExchangeName(), BuiltinExchangeType.DIRECT);
            channel.basicPublish(rabbitMQ.getExchangeName(), rabbitMQ.getRoutingKey(), null, rabbitMQ.getMessage().getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] SendMessageForDirectExchange '" + rabbitMQ.getRoutingKey() + "':'" + rabbitMQ.getRoutingKey() + "'");
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.getStackTrace();
        }


    }

    @Override
    public void recvMessageForDirectExchange(RabbitMQ rabbitMQ) {
        ConnectionFactory factory = RabbitMQ.setRabbitForFactory(rabbitMQ);
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(rabbitMQ.getQueueName(), true, false, false, null);
            channel.exchangeDeclare(rabbitMQ.getExchangeName(), BuiltinExchangeType.DIRECT);
            channel.queueBind(rabbitMQ.getQueueName(), rabbitMQ.getExchangeName(), rabbitMQ.getRoutingKey());
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
            };
            channel.basicConsume(rabbitMQ.getQueueName(), true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.getStackTrace();
        }


    }

    @Override
    public void sendMessageForTopicExchange(RabbitMQ rabbitMQ) {
        ConnectionFactory factory = RabbitMQ.setRabbitForFactory(rabbitMQ);
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicPublish(rabbitMQ.getExchangeName(), rabbitMQ.getRoutingKey(), null, rabbitMQ.getMessage().getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] SentMessageForTopicExchange '" + rabbitMQ.getRoutingKey() + "':'" + rabbitMQ.getRoutingKey() + "'");
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    @Override
    public void recvMessageForTopicExchange(RabbitMQ rabbitMQ) {
        ConnectionFactory factory = RabbitMQ.setRabbitForFactory(rabbitMQ);
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(rabbitMQ.getQueueName(), true, false, false, null);
            channel.exchangeDeclare(rabbitMQ.getExchangeName(), BuiltinExchangeType.TOPIC);
            channel.queueBind(rabbitMQ.getQueueName(), rabbitMQ.getExchangeName(), rabbitMQ.getRoutingKey());
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] recvMessageForTopicExchange '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
            };
            channel.basicConsume(rabbitMQ.getQueueName(), true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.getStackTrace();
        }

    }


    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


}