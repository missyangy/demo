package com.cn.demo.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 *
 * @author :donglianpo
 * @Data :2018/11/23 13:27
 **/
@Data
@ToString
@Table(name = "tbl_partner")
public class Partner implements Serializable {
    private static final long serialVersionUID = 1L;
    //组织机构编码
    private String operatorID;
    //内部公司标识
    private String orgNo;
    //公司id
    private String cpyId;
    //运营商密钥
    private String tokenSecret;
    //是否有充电流程
    private String valid;
    //第三方token获取地址
    private String thirdTokenUrl;
    //第三方平台密钥
    private String thirdTokenSecret;
    //加密密匙
    private String aesSecret;
    //初始化向量
    private String aesIv;
    //签名密匙
    private String sigSecret;
    //订单推送间隔，
    private String timeInterval;
    //推送订单URL
    private String pushOrderUrl;
    //接口传递密钥,消息密钥,消息密钥向量,签名密钥
    private String secret;
    //推送实时数据URL
    private String pushChargeStatusUrl;
    //推送状态URL
    private String pushEquipStatusUrl;
    //推送启动充电URL
    private String pushStartUrl;
    //推送停止充电URL
    private String pushStopUrl;
    //推送订单对账结果url
    private String pushOrderCheckUrl;
    //兼容字段，双方秘钥不统一，或特殊业务时使用
    private String compatible;

}
