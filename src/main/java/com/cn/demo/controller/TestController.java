package com.cn.demo.controller;

import com.cn.demo.model.User;
import com.cn.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: demo
 * @description: 测试类
 * @author: DongLianPo
 * @create: 2018/12/11 16:06
 **/
@RestController
public class TestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    public TestController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @RequestMapping("/queryAll")
    public List<User> queryAll() {
        return userService.queryAll();
    }


    @RequestMapping("/query")
    public List<User> query() {
        String sql = "select * from tbl_user";
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(User.class));
    }

    @RequestMapping("/queryUser")
    public List<User> queryUser() {
        return userService.queryUser();
    }

}
