package com.example.demo.mq;

import com.example.demo.entity.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author GuoJingyuan
 * @date 2019/9/23
 */
@Component
@RabbitListener(queues = "emailQue")
public class EmailReceiver {
    private static final Logger logger = LoggerFactory.getLogger(EmailReceiver.class);

    @Autowired
    private JavaMailSender mailSender;

    @RabbitHandler
    public void emailSendHandler(Email email) {
        logger.info("receiver");
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        if (email != null && !StringUtils.isEmpty(email.getFrom())) {
            try {
                //true表示需要创建一个multipart message
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom(email.getFrom());
                helper.setTo(email.getTo());
                helper.setSubject(email.getSubject());
                helper.setText(email.getContext(), true);

                String filePath = email.getFilePath();
                if (!StringUtils.isEmpty(filePath)) {
                    FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
                    String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
                    helper.addAttachment(fileName, fileSystemResource);
                }

                mailSender.send(mimeMessage);
                logger.info("html邮件发送成功");
            } catch (MessagingException e) {
                logger.error("发送html邮件时发生异常！", e);
            }
        }
    }
}
