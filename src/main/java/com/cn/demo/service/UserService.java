package com.cn.demo.service;

import com.cn.demo.model.User;

import java.util.List;

/**
 * @program: demo
 * @description: 用户信息配置
 * @author: DongLianPo
 * @create: 2018/12/11 16:11
 **/
public interface UserService {

    /**
     * 1:查询所有信息
//     *
     * @return
     */
    List<User> queryAll();

    /**
     * 2: 根据名称查询用户信息
     *
     * @param name
     * @return
     */
    User queryByName(String name);

    /**
     * 3：查询所有信息
     *
     * @return
     */
    List<User> queryUser();

}
