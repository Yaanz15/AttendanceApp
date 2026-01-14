package com.vico.attendance.repository;

import com.vico.attendance.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffRepo extends JpaRepository<Staff, Long> {

    @Query("SELECT s.name FROM Staff s")
    List<String> getStaffNameRepo();
}



//kosong sbb tk de interact dgn database