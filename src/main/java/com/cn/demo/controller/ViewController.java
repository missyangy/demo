package com.cn.demo.controller;

import com.cn.demo.model.Mail;
import com.cn.demo.model.User;
import com.cn.demo.util.JavaMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/test")
    public String views() {
        String age = request.getParameter("age");
        String passWorld = request.getParameter("passWorld");
        String phone = request.getParameter("phone");
        request.setAttribute("title", "哇哦，我进来了");
        request.setAttribute("desc", "欢迎进入系统");
        User user = new User();
        user.setAge(age);
        user.setPassWorld(passWorld);
        user.setPhone(phone);
        request.setAttribute("user", user);
        return "index";
    }

    @RequestMapping("/sendEmail")
    @ResponseBody
    public String test() {
        Mail mail = new Mail();
        mail.setSendEe(request.getParameter("sendEc"));
        mail.setSendCc(request.getParameter("sendCc"));
        mail.setEmail("dlp1523@126.com");
        mail.setAuth("Yang4233");
        mail.setSubject(request.getParameter("sendSubject"));
        mail.setMessage(request.getParameter("message"));
        JavaMailUtil.sendEmail(mail);
        return "success";
    }


}
