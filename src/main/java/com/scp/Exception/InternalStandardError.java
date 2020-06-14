package com.scp.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum InternalStandardError {

    AUTH_CLIENT_ERROR("client error occurred during auth service call", HttpStatus.BAD_REQUEST),
    AUTH_SERVER_ERROR("server error occurred during auth service call", HttpStatus.INTERNAL_SERVER_ERROR),
    PERSON_CLIENT_ERROR("client error occurred during person service call", HttpStatus.BAD_REQUEST),
    PERSON_SERVER_ERROR("server error occurred during person service call", HttpStatus.INTERNAL_SERVER_ERROR);


    private String errorMessage;
    private HttpStatus httpStatus;
}
