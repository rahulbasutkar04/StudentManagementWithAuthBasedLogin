package com.example.JWTAuth.dto;

public class EnrollmentRequest {

    private Integer subjectId;

    public EnrollmentRequest() {
    }

    public EnrollmentRequest(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) { // Changed to Integer
        this.subjectId = subjectId;
    }

}
