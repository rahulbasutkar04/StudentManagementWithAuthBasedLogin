package com.example.JWTAuth.controller;

import com.example.JWTAuth.dto.EnrollmentRequest;
import com.example.JWTAuth.dto.RequestResponse;
import com.example.JWTAuth.entity.Subject;
import com.example.JWTAuth.respository.SubjectRepository;
import com.example.JWTAuth.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminUserController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/public/subjects")
    public ResponseEntity<Object> getAllSubjects() {
        return ResponseEntity.ok(subjectRepository.findAll());
    }

    @PostMapping("/admin/saveSubject")
    public ResponseEntity<Object> saveSubject(@RequestBody RequestResponse request) {
        Subject subjectToSave = new Subject();
        subjectToSave.setName(request.getName());
        return ResponseEntity.ok(subjectRepository.save(subjectToSave));
    }

    @PostMapping("/student/enroll")
    public ResponseEntity<Object> enrollStudent(@RequestBody EnrollmentRequest enrollmentRequest) {
        try {
            return ResponseEntity.ok(enrollmentService.enrollInSubject(enrollmentRequest));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/admin/enrollments")
    public ResponseEntity<Object> getAllEnrollments(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7);
            return ResponseEntity.ok(enrollmentService.getAllEnrollments());
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body("Access denied");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
