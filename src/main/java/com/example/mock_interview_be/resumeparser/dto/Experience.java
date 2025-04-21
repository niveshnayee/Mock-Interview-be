package com.example.mock_interview_be.resumeparser.dto;

public class Experience {
    private String company;
    private String title;
    private String start_date;  // e.g., "2022-01"
    private String end_date;    // e.g., "2023-12" or "Present"
    private int duration;       // Duration in months
    private String responsibilities;

    public Experience(String company, String title, String start_date, String end_date, int duration, String responsibilities) {
        this.company = company;
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.duration = duration;
        this.responsibilities = responsibilities;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }
}
