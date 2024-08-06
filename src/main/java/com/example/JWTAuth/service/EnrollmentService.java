package com.example.JWTAuth.service;

import com.example.JWTAuth.dto.EnrollmentRequest;
import com.example.JWTAuth.entity.Enrollment;
import com.example.JWTAuth.entity.Subject;
import com.example.JWTAuth.entity.OurUsers;
import com.example.JWTAuth.respository.EnrollmentRepository;
import com.example.JWTAuth.respository.OurUserRepository;
import com.example.JWTAuth.respository.SubjectRepository;
import com.example.JWTAuth.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private OurUserRepository ourUserRepository;

    @Autowired
    private JWTUtils jwtUtils;

    public Enrollment enrollInSubject(EnrollmentRequest enrollmentRequest) throws ResourceNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        OurUsers student = ourUserRepository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Subject subject = subjectRepository.findById(enrollmentRequest.getSubjectId()).orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(student);
        enrollment.setSubject(subject);
        return enrollmentRepository.save(enrollment);
    }

    public Iterable<Enrollment> getAllEnrollments() throws ResourceNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        OurUsers user = ourUserRepository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!user.getRole().contains("ADMIN")) {
            throw new SecurityException("Access denied");
        }

        return enrollmentRepository.findAll();
    }
}
