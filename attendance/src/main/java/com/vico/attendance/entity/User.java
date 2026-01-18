package com.vico.attendance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"user\"") // user is a reserved keyword in PostgreSQL
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(length = 150)
    private String name;

    @Column(name = "access_role", length = 50)
    private String accessRole;
}