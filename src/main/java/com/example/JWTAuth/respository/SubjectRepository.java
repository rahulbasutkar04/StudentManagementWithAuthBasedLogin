package com.example.JWTAuth.respository;

import com.example.JWTAuth.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {

}
