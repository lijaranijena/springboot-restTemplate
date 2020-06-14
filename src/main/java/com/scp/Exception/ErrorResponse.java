package com.scp.Exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {

    private String statusCode;
    private String errorMessage;
    private String time;
}
