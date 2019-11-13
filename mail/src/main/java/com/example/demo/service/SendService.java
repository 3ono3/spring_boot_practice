package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author GuoJingyuan
 * @date 2019/9/23
 */
@Component
public class SendService {

    private static final Logger logger = LoggerFactory.getLogger(SendService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    public void sendMail(String to, String subject, String context) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(context);

        try {
            mailSender.send(mailMessage);
            logger.info("简单邮件发送成功");
        } catch (MailException e) {
            e.printStackTrace();
            logger.info("邮件发送失败");
        }
    }

    public void sendHtmlMail(String to, String subject, String content) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(mimeMessage);
            logger.info("html邮件发送成功");
        } catch (MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
        }
    }

    public void sendFileMail(String to, String subject, String content, String filePath) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            File file = new File(filePath);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, fileSystemResource);

            mailSender.send(mimeMessage);
            logger.info("附件邮件发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("附件邮件发送失败");
        }
    }

    public void sendInlineMail(String to, String subject, String content, String filePath, String resourcesId) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            String[] tos = {to,from};
            helper.setTo(tos);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
            helper.addInline(resourcesId, fileSystemResource);

            mailSender.send(mimeMessage);
            logger.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("嵌入静态资源的邮件发送失败");
            e.printStackTrace();
        }
    }
}
