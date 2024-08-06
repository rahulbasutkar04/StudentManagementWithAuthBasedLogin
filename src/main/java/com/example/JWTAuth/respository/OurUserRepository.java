package com.example.JWTAuth.respository;

import com.example.JWTAuth.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OurUserRepository extends JpaRepository<OurUsers,Integer> {
    Optional<OurUsers> findByEmail(String email);
}
