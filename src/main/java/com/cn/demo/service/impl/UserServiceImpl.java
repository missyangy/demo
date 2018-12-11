package com.cn.demo.service.impl;

import com.cn.demo.dao.UserMapper;
import com.cn.demo.model.User;
import com.cn.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    private final User user;

    public UserServiceImpl(User user) {
        this.user = user;
    }


    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public User queryByName(String name) {
        return user;
    }

    @Override
    public List<User> queryUser() {
        return userMapper.queryUser();
    }
}
