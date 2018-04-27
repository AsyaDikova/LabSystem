package org.softuni.laboratory.core.entities.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class ErrorMessage {

    private String message;
    private boolean success;

    public ErrorMessage() {
        this.setSuccess(false);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    private void setSuccess(boolean isSuccess) {
        this.success = isSuccess;
    }
}
