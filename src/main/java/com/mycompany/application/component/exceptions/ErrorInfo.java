package com.mycompany.application.component.exceptions;

public class ErrorInfo {
    public final String url;
    public final String exception;

    public ErrorInfo(String url, Exception exception) {
        this.url = url;
        this.exception = exception.getLocalizedMessage();
    }
}