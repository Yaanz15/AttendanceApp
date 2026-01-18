package com.vico.attendance.repository;

import com.vico.attendance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("SELECT u.name FROM User u WHERE u.userId = :id")
    String findNameById(@Param("id") Long id);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);
}
