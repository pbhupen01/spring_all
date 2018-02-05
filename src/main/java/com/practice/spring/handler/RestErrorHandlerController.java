package com.practice.spring.handler;

import com.practice.spring.dto.ErrorResponse;
import com.practice.spring.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class RestErrorHandlerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request)
    {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.BAD_REQUEST,"Could not read JSON document.", ex));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request)
    {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        return buildResponseEntity(
                new ErrorResponse(HttpStatus.NOT_FOUND, error, ex));
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleException(UserNotFoundException ex) {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse baseErrorDTO) {
        return new ResponseEntity(baseErrorDTO, baseErrorDTO.getStatus());
    }
}