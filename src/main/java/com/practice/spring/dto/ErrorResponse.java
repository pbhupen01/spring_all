package com.practice.spring.dto;

import lombok.Data;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ErrorResponse {

    private HttpStatus status;
    private String timestamp;
    private String message;
    private String debugMessage;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        this.timestamp = formatter.format(new Date());
    }

    public ErrorResponse(HttpStatus status, String message, Exception ex) {
        this(status, message);
        this.debugMessage = ExceptionUtils.getStackTrace(ex);
    }

    public String toString()
    {
        return String.format("HttpStatus: %s. Error Message: %s", status.toString(), message);
    }
}
