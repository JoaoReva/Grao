package com.grao.exception;

import java.lang.Exception;

public class ErrorException extends Exception { 	
    public ErrorException(String errorMessage) {
        super(errorMessage);
    }
}