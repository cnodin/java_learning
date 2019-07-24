package com.wwyl.swan.common.mail;

import com.wwyl.lark.util.constant.GlobalConstant;
import com.wwyl.lark.util.text.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/02/2018
 * Time: 01:53
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Service
@Slf4j
public class MailService {

    @Autowired
    private MailContentBuilder mailContentBuilder;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.profiles.active}")
    private String environment;
    @Async
    public void sendMail(String templateName, Mail mail) {
        try {
            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, Charsets.UTF_8_NAME);
                messageHelper.setFrom(mail.getFrom());
                messageHelper.setTo(mail.getTo());
                messageHelper.setCc(mail.getCc());
                messageHelper.setSubject(mail.getSubject()+"【测试】");
                if(environment.startsWith(GlobalConstant.ENVIRONMENT_PRO)){
                    messageHelper.setSubject(mail.getSubject());
                }
                String text = mailContentBuilder.build(templateName, mail.getVariables());
                log.info("email content: {}", text);
                messageHelper.setText(text, mail.getHtml());
            };
            mailSender.send(messagePreparator);
            throw new RuntimeException("test exception");
        }catch(Exception e) {
            log.error("send mail failure, template: {}, to: {},{}", templateName, String.join(",", mail.getTo()),e);
        }

    }



}
