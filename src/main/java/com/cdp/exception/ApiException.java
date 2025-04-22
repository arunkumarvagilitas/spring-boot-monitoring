package com.cdp.exception;

import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Setter
public class ApiException extends Exception {
    public String message;
    public HttpStatusCode httpStatusCode;
    public ApiException(String message, HttpStatusCode httpStatusCode) {
        super(message);
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}