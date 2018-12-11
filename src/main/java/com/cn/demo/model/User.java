package com.cn.demo.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
@ConfigurationProperties(prefix = "model")
public class User {
    private int userId;
    private String userName;
    private String passWorld;
    private String age;
    private String phone;


}
