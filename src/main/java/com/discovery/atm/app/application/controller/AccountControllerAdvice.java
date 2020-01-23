package com.discovery.atm.app.application.controller;

import com.discovery.atm.app.exception.ResourceNotFound;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice( assignableTypes = {AccountController.class, ReportController.class})
public class AccountControllerAdvice {
    @ExceptionHandler({
            ResourceNotFound.class
    })
    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    private String handleValidationErrors(Exception e) {
        Throwable cause = e.getCause();

        if (hasUnderlyingCause(cause)) {
            return "Requested resource could not be found. With cause: "+ cause.getCause().getMessage();
        }
        return "Requested resource could not be found. With cause: "+ e.getMessage();
    }

    private boolean hasUnderlyingCause(Throwable cause) {
        return cause != null && cause.getCause() != null;
    }
}
