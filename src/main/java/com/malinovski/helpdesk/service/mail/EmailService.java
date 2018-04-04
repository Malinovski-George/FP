/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package com.malinovski.helpdesk.service.mail;

import com.malinovski.helpdesk.dto.MailDto;
import com.malinovski.helpdesk.model.User;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class EmailService {

    private static final String EMAIL_SIMPLE_TEMPLATE_NAME = "html/email-simple";

    private final String SUBJECT_NEW = "New ticket for approval";
    private final String SUBJECT_APPROVED = "Ticket was approved";
    private final String SUBJECT_DECLINED = "Ticket was declined";
    private final String SUBJECT_CANCELED = "Ticket was cancelled";
    private final String SUBJECT_DONE = "Ticket was done";
    private final String SUBJECT_FEEDBACK = "Feedback was provided";

    private final String BODY_NEW = " is waiting for your approval.";
    private final String BODY_APPROVED = " was approved by the Manager.";
    private final String BODY_DECLINED = " was declined by the Manager.";
    private final String BODY_CANCELED_MANAGER = " was cancelled by the Manager.";
    private final String BODY_CANCELED_ENGINEER = " was cancelled by the Engineer.";
    private final String BODY_DONE = " was done by the Engineer.\n" +
            "Please provide your feedback clicking on the ticket ID.";
    private final String BODY_FEEDBACK = "The feedback was provided.";


    private JavaMailSender mailSender;
    private TemplateEngine htmlTemplateEngine;

    public EmailService(JavaMailSender mailSender, TemplateEngine htmlTemplateEngine) {
        this.mailSender = mailSender;
        this.htmlTemplateEngine = htmlTemplateEngine;
    }

    public void sendSimpleMail(MailDto mailDto, final String recipientEmail) throws MessagingException {

        Locale locale = new Locale("en_GB");

        final Context ctx = new Context(locale);

        ctx.setVariable("MailDto", mailDto);
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject(mailDto.getSubjectMessage());

        //TODO вынести в пропертиес
        message.setFrom("HelpDesk@support.com");
        message.setTo(recipientEmail);

        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_SIMPLE_TEMPLATE_NAME, ctx);
        message.setText(htmlContent, true /* isHtml */);

        this.mailSender.send(mimeMessage);
    }

    public void sendEmailStatusNew(int ticketId, User user) {

        MailDto mailDto = new MailDto().
                newBuilder()
                .setBodyText(BODY_NEW)
                .setSubjectMessage(SUBJECT_NEW)
                .setId(ticketId)
                .setUserName(user.toString())
                .build();

        try {
            sendSimpleMail(mailDto, user.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailStatusApprove(int ticketId, User user) {

        MailDto mailDto = new MailDto().
                newBuilder()
                .setBodyText(BODY_APPROVED)
                .setSubjectMessage(SUBJECT_APPROVED)
                .setId(ticketId)
                .setUserName(user.toString())
                .build();

        try {
            sendSimpleMail(mailDto, user.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailStatusDecline(int ticketId, User user) {

        MailDto mailDto = new MailDto().
                newBuilder()
                .setBodyText(BODY_DECLINED)
                .setSubjectMessage(SUBJECT_DECLINED)
                .setId(ticketId)
                .setUserName(user.toString())
                .build();

        try {
            sendSimpleMail(mailDto, user.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailStatusCancelledEngineer(int ticketId, User user) {

        MailDto mailDto = new MailDto().
                newBuilder()
                .setBodyText(BODY_CANCELED_ENGINEER)
                .setSubjectMessage(SUBJECT_CANCELED)
                .setId(ticketId)
                .setUserName(user.toString())
                .build();

        try {
            sendSimpleMail(mailDto, user.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailStatusCancelledManager(int ticketId, User user) {

        MailDto mailDto = new MailDto().
                newBuilder()
                .setBodyText(BODY_CANCELED_MANAGER)
                .setSubjectMessage(SUBJECT_CANCELED)
                .setId(ticketId)
                .setUserName(user.toString())
                .build();

        try {
            sendSimpleMail(mailDto, user.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailStatusDone(int ticketId, User user) {

        MailDto mailDto = new MailDto().
                newBuilder()
                .setBodyText(BODY_DONE)
                .setSubjectMessage(SUBJECT_DONE)
                .setId(ticketId)
                .setUserName(user.toString())
                .build();

        try {
            sendSimpleMail(mailDto, user.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailStatusFeedback(int ticketId, User user) {

        MailDto mailDto = new MailDto().
                newBuilder()
                .setBodyText(BODY_FEEDBACK)
                .setSubjectMessage(SUBJECT_FEEDBACK)
                .setId(ticketId)
                .setUserName(user.toString())
                .build();

        try {
            sendSimpleMail(mailDto, user.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
