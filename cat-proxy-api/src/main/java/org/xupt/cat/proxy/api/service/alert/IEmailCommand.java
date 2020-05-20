package org.xupt.cat.proxy.api.service.alert;

import org.springframework.mail.MailException;

public interface IEmailCommand {
    /**
     * 发送普通文本
     * @param to
     * @param nick
     * @param from
     * @param subject
     * @param text
     */
    void setTextMail(String[] to, String nick, String from, String subject, String text) throws MailException;
}
