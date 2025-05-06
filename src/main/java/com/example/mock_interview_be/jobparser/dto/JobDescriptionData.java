package com.example.mock_interview_be.jobparser.dto;

import java.util.List;

public class JobDescriptionData {

    private String title;
    private List<String> keySkills;
    private String responsibility;
    private String requirements;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getKeySkills() {
        return keySkills;
    }

    public void setKeySkills(List<String> keySkills) {
        this.keySkills = keySkills;
    }

    public String getResponsibility() {
        return responsibility;
    }

    @Override
    public String toString() {
        return "JobDescriptionData{" +
                "title='" + title + '\'' +
                ", keySkills=" + keySkills +
                ", responsibility='" + responsibility + '\'' +
                ", requirements='" + requirements + '\'' +
                '}';
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
}
