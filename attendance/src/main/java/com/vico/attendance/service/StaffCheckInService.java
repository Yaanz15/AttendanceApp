package com.vico.attendance.service;

import com.vico.attendance.repository.CheckInRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@AllArgsConstructor  //extension from lombok, kalau tk de kena buat contrctor sendiri
public class StaffCheckInService {

    private final CheckInRepo checkinrepo;

    public void staffCheckIn (String name, String email, Long phone_num){

        LocalDate currentdate = LocalDate.now();
        LocalTime currenttime = LocalTime.now();
        checkinrepo.insertintotable(name, email, phone_num, currentdate, currenttime);
    }
}

