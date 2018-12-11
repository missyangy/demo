package com.cn.demo.controller;

import com.cn.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Slf4j
public class UserControllerTest extends TestController {

    public UserControllerTest(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Test
    public List<User> queryAll() {
        return null;
    }

    @Test
    public User queryByName() {

        return null;
    }
}