package com.example.JWTAuth.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private OurUsers user;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;


    public void setUser(OurUsers user) {
        this.user = user;
    }


    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
