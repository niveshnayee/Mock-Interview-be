package com.example.mock_interview_be.jobparser.util;

public class JobValidationUtil {
    private static final long MIN_SIZE = 100;

    public static void validateJobDescription(String description){
        if(description == null || description.trim().isEmpty()){
            throw new IllegalArgumentException("Job description cannot be empty.");
        }
        if(description.length() <= MIN_SIZE){
            throw new IllegalArgumentException("Job description is too short to parse meaningfully.");
        }
    }

}
