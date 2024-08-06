package com.example.JWTAuth.dto;

public class EnrollmentRequest {

    private Integer subjectId; // Changed to Integer

    public EnrollmentRequest() {
    }

    public EnrollmentRequest(Integer subjectId) { // Changed to Integer
        this.subjectId = subjectId;
    }

    public Integer getSubjectId() { // Changed to Integer
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) { // Changed to Integer
        this.subjectId = subjectId;
    }
}
