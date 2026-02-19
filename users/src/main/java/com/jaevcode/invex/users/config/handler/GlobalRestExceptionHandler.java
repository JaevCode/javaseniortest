package com.jaevcode.invex.users.config.handler;

import com.jaevcode.invex.users.common.exception.BusinessValidationException;
import com.jaevcode.invex.users.common.util.ResponseFactoryUtil;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> messages = ex.getBindingResult().getAllErrors().stream().map(error -> {
            FieldError fieldError = (FieldError) error;
            return fieldError.getField() + " " + fieldError.getDefaultMessage();
        }).toList();

        return ResponseFactoryUtil.createErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Validation Failed",
                messages.toArray(new String[0])
        );
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<?> handleMethodValidationErrors(HandlerMethodValidationException ex) {
        List<String> messages = ex.getParameterValidationResults().stream()
                .map(result -> {
                    String fieldName = result.getMethodParameter().getParameter().getName();
                    String error = result.getResolvableErrors().stream()
                            .map(MessageSourceResolvable::getDefaultMessage)
                            .collect(Collectors.joining(", "));
                    return fieldName + ": " + error;
                }).toList();

        return ResponseFactoryUtil.createErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Validation Failed",
                messages.toArray(new String[0])
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> messages = ex.getConstraintViolations().stream().map(error -> {
            if (error == null) return "null";
            String[] path = error.getPropertyPath().toString().split("\\.");
            String field = path[path.length - 1];
            return field + ": " + error.getMessage();
        }).collect(Collectors.toList());

        return ResponseFactoryUtil.createErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Validation Failed",
                messages.toArray(new String[0])
        );
    }

    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<?> handleBusinessValidationException(BusinessValidationException ex) {
        return ResponseFactoryUtil.createErrorResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        return ResponseFactoryUtil.createErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                ex.getMessage()
        );
    }
}
