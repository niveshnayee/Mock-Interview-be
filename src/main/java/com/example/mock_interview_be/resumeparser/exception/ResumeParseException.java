package com.example.mock_interview_be.resumeparser.exception;

public class ResumeParseException extends RuntimeException{
    public ResumeParseException(String message){
        super(message);
    }

    public ResumeParseException(String message, Throwable cause){
        super(message, cause);
    }
}
