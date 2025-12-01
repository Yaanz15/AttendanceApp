package com.vico.attendance.repository;

import com.vico.attendance.entity.StaffCheckIn;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CheckInRepo extends JpaRepository<StaffCheckIn, Long> {

    @Modifying
    @Transactional    //@Modifying and @Transactional hanya untuk modify table (insert, update....
    @Query(
            value = "INSERT INTO staffcheckin (name, email, phone_num, checkin_date, checkin_time) values (:name, :email, :phone_num, :checkin_date, :checkin_time)" ,
            nativeQuery = true
    )
    void insertintotable(String name, String email, Long phone_num, LocalDate checkin_date, LocalTime checkin_time);

    @Query("SELECT s FROM StaffCheckIn s")
    List<StaffCheckIn> findAllStaff();

}
