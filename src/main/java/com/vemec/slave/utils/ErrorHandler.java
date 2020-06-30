package com.vemec.slave.utils;

import org.springframework.http.HttpStatus;

public class ErrorHandler {
    private String status;
    private Integer status_code;
    private String message;

    public ErrorHandler(HttpStatus status, String message) {
        this.status = status.getReasonPhrase();
        this.status_code = status.value();
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
