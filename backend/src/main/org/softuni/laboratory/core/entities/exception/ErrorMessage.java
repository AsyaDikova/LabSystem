package org.softuni.laboratory.core.entities.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class ErrorMessage {

    private String message;
    private boolean isSuccess;

    public ErrorMessage() {
    }

    public ErrorMessage(String message, boolean isSuccess) {
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
