package com.example.mock_interview_be.resumeparser.dto;

import java.util.List;

public class StructuredResumeResponse {
    private String id;
    private ResumeData data;

    public StructuredResumeResponse(String id, ResumeData data) {
        this.id = id;
        this.data = data;
    }

    public String getFull_name() {
        return data.getFullName();
    }

    public String getEmail() {
        return data.getEmail();
    }
}
