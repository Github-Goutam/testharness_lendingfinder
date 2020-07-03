package com.rbs.lendingfinder.config;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rbs.lendingfinder.common.CustomErrorResponse;
import com.rbs.lendingfinder.common.LFException;

@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {
 	@ExceptionHandler(LFException.class)
    public ResponseEntity<CustomErrorResponse> customHandleNotFound(LFException ex, WebRequest request) {
		CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setMessage(ex.getMessage());
        errors.setErrors(ex.getErrors());
        errors.setStatus(ex.getStatus().value());
        return new ResponseEntity<>(errors, ex.getStatus());

    }
}