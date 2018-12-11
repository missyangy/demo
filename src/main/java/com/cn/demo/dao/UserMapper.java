package com.cn.demo.dao;

import com.cn.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "userMapper")
public interface UserMapper {
    /**
     * @param name
     * @return
     */
    User queryByName(String name);

    @Select("select * from tbl_user")
    List<User> queryAll();

    List<User> queryUser();

}
