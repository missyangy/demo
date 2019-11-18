package com.cn.demo.util.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: demo
 * @description: ${Description}
 * @author: DongLianPo
 * @create: 2019/02/19 13:49
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitmqConfigTest extends RabbitmqConfig {
    @Autowired
    private Environment environment;


    @Test
    public void RabbitProperty() {
        String property = environment.getProperty("log.user.queue.name");
        System.out.println(property);
        String property1 = environment.getProperty("log.user.exchange.name");
        System.out.println(property1);
        String property2 = environment.getProperty("log.user.routing.key.name");
        System.out.println(property2);

    }


    @Test
    public void TestQueue(){



    }

}