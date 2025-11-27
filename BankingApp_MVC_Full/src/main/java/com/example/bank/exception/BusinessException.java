package com.example.bank.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String msg) { super(msg); }
    public BusinessException(String msg, Throwable cause) { super(msg, cause); }
}
