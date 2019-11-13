package com.example.demo;

import com.example.demo.entity.Email;
import com.example.demo.mq.EmailSender;
import com.example.demo.mq.HelloSender;
import com.example.demo.mq.HelloSender2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private HelloSender helloSender;
    @Autowired
    private HelloSender2 helloSender2;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void manyToMany() {
        for (int i=0;i<100;i++){
            helloSender.send(i);
            helloSender2.send(i);
        }
    }

    @Test
    public void sendEmail() {
        Email email = new Email();
        String from = "abbbbb2@163.com";
        String[] to = {"jingyuan71@163.com"};
        Context context = new Context();
        context.setVariable("id","001");
        String emailContext = templateEngine.process("emailTemplate", context);
        String filePath = "E:\\temp\\testSendMail.txt";

        email.setFrom(from);
        email.setTo(to);
        email.setFilePath(filePath);
        email.setSubject("验证邮件");
        email.setContext(emailContext);
        emailSender.send(email);
    }

}
