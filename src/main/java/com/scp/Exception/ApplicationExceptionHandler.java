package com.scp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handlerException(Exception exception){
        ErrorResponse errorResponse=ErrorResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .errorMessage(exception.getMessage())
                .time(LocalDateTime.now().toString())
                .build();
        return errorResponse;
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleApException(AppException appException){
        InternalStandardError error = appException.getInternalStandardError();

        ErrorResponse errorResponse=ErrorResponse.builder()
                .statusCode(error.getHttpStatus().toString())
                .errorMessage(error.getErrorMessage())
                .time(LocalDateTime.now().toString())
                .build();
        return new ResponseEntity<>(errorResponse, error.getHttpStatus());
    }
}
