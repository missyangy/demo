package com.cn.demo.listner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @program: charge-api
 * @description: 监听器配置
 * @author: DongLianPo
 * @create: 2018-12-04 16:41
 **/
@WebListener
public class CecListener implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(CecListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("初始化...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("销毁...");

    }
}
