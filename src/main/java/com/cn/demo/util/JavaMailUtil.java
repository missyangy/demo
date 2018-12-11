
package com.cn.demo.util;


import com.cn.demo.model.Mail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class JavaMailUtil {
    private static final SimpleDateFormat FORMATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 定制服务
     *
     * @return
     */
    public static Properties getProperties() {
        Properties prop = new Properties();
        //发送服务器
        prop.setProperty("mail.smtp.host", "smtp.126.com");
        //服务默认端口
        prop.setProperty("mail.stmp.port", "25");
        //服务开启验证
        prop.setProperty("mail.smtp.auth", "true");
        //服务开启ssl
        prop.put("mail.smtp.starttls.enable", "true");
        return prop;
    }

    /**
     * 发送邮件
     *
     * @param mail
     */
    public static void sendEmail(Mail mail) {
        try {
            //获取配置文件
            Properties prop = getProperties();
            //1：创建session
            Session session = Session.getInstance(prop);
            session.setDebug(true);
            //2:通过session得到transport对象
            Transport ts = session.getTransport();
            //3:创建邮件
            Message message = createSimpleMail(mail, session);
            //MimeMessage message = createImgMail(mail.getEmail(), mail.getSubject(), session);
            ts.connect(mail.getEmail(), mail.getAuth());
            //5:发送邮件getAllRecipients()获取所有要发送的收件人(to,cc,Bcc)
            ts.sendMessage(message, message.getAllRecipients());
            //6:关闭8
            ts.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 定制内容格式（简单文本）
     *
     * @param session
     * @return
     * @throws Exception
     */
    private static MimeMessage createSimpleMail(Mail mail, Session session) {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        try {
            //指明邮件的发件人
            message.setFrom(mail.getEmail());
            //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getSendEe()));
            //指明邮件的抄送人
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(mail.getSendCc()));
            //邮件的标题
            message.setSubject(mail.getSubject());
            //设置发送日期
            message.setSentDate(new Date());
            //设置文档内容（纯文本格式）text/html;charset=UTF-8
            message.setText(mail.getMessage(), "utf-8");
            //保存并生成最终内容
            message.saveChanges();
            //邮件的文本内容（支持其他格式）
            message.setContent("你好啊！你是", "text/html;charset=UTF-8");
        } catch (Exception e) {
            e.getMessage();
        }
        return message;
    }

    /**
     * 发送附件
     *
     * @param session
     * @return
     */
    private static MimeMessage createImgMail(Mail mail, Session session) {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        try {
            //发件人
            message.setFrom(new InternetAddress(mail.getEmail()));
            //邮件主题
            message.setSubject(mail.getSubject());
            //发送时间
            message.setSentDate(new Date());
            //收件人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getSendEe()));
            //指明邮件的抄送人
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(mail.getSendCc()));
            //指明邮件的密送
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(mail.getSendCc()));
            //准备邮件正文数据
            MimeBodyPart text = new MimeBodyPart();
            text.setContent("这是一封<font color='red'>图片</font>邮件", "text/html;charset=UTF-8");
            // 准备图片数据
            MimeBodyPart image = new MimeBodyPart();
            DataHandler dh = new DataHandler(new FileDataSource("src/main/resources/static/test.pdf"));
            image.setHeader("Content-Type", "image/png");
            image.setDataHandler(dh);
            image.setFileName(dh.getName());
            //image.setContentID("test.jpeg"); 描述数据关系
            MimeMultipart mm = new MimeMultipart("related");
            mm.addBodyPart(text);
            mm.addBodyPart(image);
            message.setContent(mm);
            message.saveChanges();
            //将创建好的邮件写入到D盘以文件的形式进行保存
            message.writeTo(new FileOutputStream("D:\\ImageMail.eml"));
        } catch (Exception e) {
            e.getMessage();
        }
        //返回创建好的邮件
        return message;
    }

    public static void main(String[] args) {
        Mail mail = Mail.getMail("dlp1523@126.com", "dlp1523@126.com", "donglianpo@wanmagroup.com", "Yang4233", "亲要买吗？有优惠哦！", "双十一大礼包!");
        sendEmail(mail);


    }

}
