package org.xupt.cat.proxy.api.service.alert.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.exception.EmailException;
import org.xupt.cat.proxy.api.service.alert.IEmailCommand;

@Service
public class EmailCommandImp implements IEmailCommand {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void setTextMail(String[] to, String nick, String from, String subject, String text) throws EmailException {
        if (to == null || to.length == 0) {
            throw new EmailException("toUser is null");
        }

        if (from == null || from.equals("")) {
            throw new EmailException("fromUser is null");
        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        if (nick != null && !nick.equals("")) {
            from = nick + "<" + from + ">";
        }
        simpleMailMessage.setFrom(from);

        mailSender.send(simpleMailMessage);
    }
}
