package com.vico.attendance.repository;

import com.vico.attendance.entity.StaffCheckIn;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;

public interface CheckInRepo extends JpaRepository<StaffCheckIn, Long> {

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO staffcheckin (name, email, phone_num, checkin_date, checkin_time) values (:name, :email, :phone_num, :checkin_date, :checkin_time)" ,
            nativeQuery = true
    )
    void insertintotable(String name, String email, Long phone_num, LocalDate checkin_date, LocalTime checkin_time);


}
