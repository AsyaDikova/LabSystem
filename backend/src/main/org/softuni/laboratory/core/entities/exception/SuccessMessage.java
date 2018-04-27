package org.softuni.laboratory.core.entities.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class SuccessMessage {
    private boolean success;

    private Object object;

    public SuccessMessage() {
        this.setSuccess(true);
    }

    public boolean success() {
        return success;
    }

    private void setSuccess(boolean success) {
        success = success;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
