package com.needle.oneline.src.common.auth.exception;

public class TokenValidFailedException extends RuntimeException{
    public TokenValidFailedException(){
        super("Failed to generate Token.");
    }
    public TokenValidFailedException(String message){
        super(message);
    }
}
