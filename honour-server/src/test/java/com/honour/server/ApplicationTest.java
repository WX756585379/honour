package com.honour.server;

import com.honour.server.utils.jedis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testSendByTopics() {
        System.out.println("aaaa");
        redisUtil.set("a", "b",7, TimeUnit.DAYS);
    }

    /**
     * 创建简单消息邮件
     */
    @Test
    public void testMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("这是主题");
        message.setText("这是内容");
        //收件人
        message.setTo("13376275127@163.com");
        //发送人
        message.setFrom("756585379@qq.com");
        mailSender.send(message);
    }

    /**
     * 创建复杂消息邮件
     */
    @Test
    public void testMail02() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是复杂消息邮件主题");
        helper.setText("<b style='color:red;'>这是复杂消息邮件内容</b>", true);
        //添加附件1
        // helper.addAttachment("1.jpg", new File("E:\\desktop\\8234.jpg"));
        //添加附件2
        // helper.addAttachment("2.docx", new File("E:\\desktop\\形势与政策课作业.docx"));
        //收件人
        helper.setTo("13376275127@163.com");
        //发送人
        helper.setFrom("756585379@qq.com");
        mailSender.send(mimeMessage);
    }
}
