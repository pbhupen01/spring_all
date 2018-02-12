package com.practice.spring.handler;

import com.practice.spring.dto.ErrorResponse;
import com.practice.spring.exception.DiaryAlreadyExistsException;
import com.practice.spring.exception.DiaryNotFoundException;
import com.practice.spring.exception.UserAlreadyExistsException;
import com.practice.spring.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@Slf4j
public class RestErrorHandlerController extends ResponseEntityExceptionHandler {

    private static String ERROR_MESSAGE = "Error occurred while processing request. Sending Error: ";

    /*@Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request)
    {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,"Could not read JSON document.", ex);
        log.error(ERROR_MESSAGE + errorResponse, ex);
        return buildResponseEntity(errorResponse);
    }*/

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request)
    {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,"Could not read JSON document.", ex);
        log.error(ERROR_MESSAGE + errorResponse, ex);
        return buildResponseEntity(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request)
    {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        log.error(ERROR_MESSAGE + errorResponse, ex);
        return buildResponseEntity(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, error, ex);
        log.error(error + ". Sending Error: "+ errorResponse, ex);
        return buildResponseEntity(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatus status,
                                                                         WebRequest request)
    {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage(), ex);
        log.error(ERROR_MESSAGE + errorResponse, ex);
        return buildResponseEntity(errorResponse);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        log.error(ERROR_MESSAGE + errorResponse, ex);
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleException(UserAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        log.error(ERROR_MESSAGE + errorResponse, ex);
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(DiaryNotFoundException.class)
    public ResponseEntity<Object> handleException(DiaryNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        log.error(ERROR_MESSAGE + errorResponse, ex);
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(DiaryAlreadyExistsException.class)
    public ResponseEntity<Object> handleException(DiaryAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        log.error(ERROR_MESSAGE + errorResponse, ex);
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleException(MethodArgumentTypeMismatchException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        log.error(ERROR_MESSAGE + errorResponse, ex);
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        log.error(ERROR_MESSAGE + errorResponse, ex);
        return buildResponseEntity(errorResponse);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity(errorResponse, errorResponse.getStatus());
    }
}
