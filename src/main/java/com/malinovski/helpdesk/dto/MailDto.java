package com.malinovski.helpdesk.dto;

import org.springframework.stereotype.Component;

@Component
public class MailDto {

    private String userName;
    private String bodyText;
    private int id;
    private String subjectMessage;

    public MailDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectMessage() {
        return subjectMessage;
    }

    public void setSubjectMessage(String subjectMessage) {
        this.subjectMessage = subjectMessage;
    }

    public static Builder newBuilder() {
        return new MailDto().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(int id) {
            MailDto.this.id = id;
            return this;
        }

        public Builder setUserName(String userName) {
            MailDto.this.userName = userName;
            return this;
        }

         public Builder setBodyText(String bodyText) {
            MailDto.this.bodyText = bodyText;
            return this;
        }

         public Builder setSubjectMessage(String subjectMessage) {
            MailDto.this.subjectMessage = subjectMessage;
            return this;
        }

        public MailDto build() {
            return MailDto.this;
        }
    }


}

