package com.example.mock_interview_be.resumeparser.dto;

import java.util.List;

public class ResumeData {
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String summary;
    private List<String> skills;
    private List<Education> education;
    private List<Experience> experience;
    private List<String> certifications;
    private List<Project> projects;
    private List<String> languages;


    public ResumeData(String fullName, String email, String phone, String address, String summary, List<String> skills, List<Education> education, List<Experience> experience, List<String> certifications, List<Project> projects, List<String> languages) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.summary = summary;
        this.skills = skills;
        this.education = education;
        this.experience = experience;
        this.certifications = certifications;
        this.projects = projects;
        this.languages = languages;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = certifications;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
}
