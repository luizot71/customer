package com.br.project.customer.registration.handler.exceptions;

import java.util.Objects;

public interface ErrorCode {

    ErrorCode.Properties getProperties();

    public static final class Properties {
        private final int errorCode;
        private final HttpCode httpCode;
        private final String title;
        private final String messageId;

        private Properties(int errorCode, HttpCode httpCode, String title, String messageId) {
            Objects.requireNonNull(messageId, "Missing mandatory description message about the error");
            this.errorCode = errorCode;
            this.httpCode = httpCode;
            this.title = title;
            this.messageId = messageId;
        }

        public static ErrorCode.Properties of(int errorCode, HttpCode httpCode, String title, String messageId) {
            return new ErrorCode.Properties(errorCode, httpCode, title, messageId);
        }

        public int getErrorCode() {
            return errorCode;
        }

        public HttpCode getHttpCode() {
            return httpCode;
        }

        public String getTitle() {
            return title;
        }

        public String getMessageId() {
            return messageId;
        }
    }
}
