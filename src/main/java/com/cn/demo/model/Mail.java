package com.cn.demo.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Mail {
    private String username;//用户名
    private String email;//发件人
    private String password;//密码
    private String port;//端口号
    private String auth;//客户端开通协议的授权码
    private String message;//邮件内容
    private String subject;//主题
    private String sendEe;//收件人
    private String sendCc;//抄送人


    public static Mail getMail(String name, String email, String password, String port, String auth, String message, String subject) {
        Mail mail = new Mail();
        mail.setUsername(name);
        mail.setAuth(auth);
        mail.setEmail(email);
        mail.setMessage(message);
        mail.setPort(port);
        mail.setPassword(password);
        mail.setSubject(subject);
        return mail;
    }

    public static Mail getMail(String email, String sendEe, String sendCc, String auth, String message, String subject) {
        Mail mail = new Mail();
        mail.setAuth(auth);
        mail.setEmail(email);
        mail.setMessage(message);
        mail.setSubject(subject);
        mail.setSendEe(sendEe);
        mail.setSendCc(sendCc);
        return mail;
    }

    public static Mail getMail(String message) {
        Mail mail = new Mail();
        mail.setMessage(message);
        return mail;
    }


}
