package com.example.demo;

import com.example.demo.service.SendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.PropertyBatchUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private SendService sendService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void contextLoads() {
        sendService.sendMail("jingyuan71@163.com","boot send mail", "hello boot");
    }

    @Test
    public void sendHtmlMail() {
        String context = "<html> <body> <h1 style=\"color:red\">hello boot HTML格式邮件</h1> </body> </html>";
        sendService.sendHtmlMail("jingyuan71@163.com", "boot send HTML mail", context);
    }

    @Test
    public void sendFileMail() {
        String filePath = "E:\\temp\\testSendMail.txt";
        sendService.sendFileMail("jingyuan71@163.com", "boot file", "详情请查看附件", filePath);
    }

    @Test
    public void sendLineMail() {
        String filePath = "E:\\temp\\abc.jpg";
        String rscId = "abc001";
        String context = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        sendService.sendInlineMail("liaoshixia@eigpay.com", "boot file", context, filePath, rscId);
    }

    @Test
    public void sendTemplateHtmlMail() {
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContext = templateEngine.process("emailTemplate", context);
        sendService.sendHtmlMail("liaoshixia@eigpay.com", "这是验证邮件", emailContext);
    }
}
