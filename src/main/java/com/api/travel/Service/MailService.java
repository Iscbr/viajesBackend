package com.api.travel.Service;

import com.api.travel.Util.Mail;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class MailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    public MailService(JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine) {
        this.javaMailSender = javaMailSender;
        this.springTemplateEngine = springTemplateEngine;
    }

    public void sendEmail(Mail mail) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(
                message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        // Se pueden enviar imagenes o docs adjuntos en el mail.
        //messageHelper.addAttachment("welcome.jpg", new ClassPathResource("static/images/welcome.jpg"));

        Context context = new Context();
        context.setVariables(mail.getModel());
        String html = springTemplateEngine.process("RegistrationConfirm", context);

        messageHelper.setTo(mail.getTo());
        messageHelper.setText(html, true);
        messageHelper.setSubject(mail.getSubject());
        messageHelper.setFrom(mail.getFrom());

        javaMailSender.send(message);

    }
}
