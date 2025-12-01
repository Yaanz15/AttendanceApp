package com.vico.attendance.service;

import com.vico.attendance.dto.StaffCheckinDto;
import com.vico.attendance.entity.StaffCheckIn;
import com.vico.attendance.repository.CheckInRepo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor  //extension from lombok, kalau tk de kena buat contrctor sendiri
public class StaffCheckInService {

    private final CheckInRepo checkinrepo;

    public ResponseEntity<?> staffCheckIn (String name, String email, Long phone_num){
// ResponseEntity<?> .... ? meaning that it can be anything (wild card)

        LocalDate currentdate = LocalDate.now();
        LocalTime currenttime = LocalTime.now();
        if (ObjectUtils.isNotEmpty(name) && ObjectUtils.isNotEmpty(email) && ObjectUtils.isNotEmpty(phone_num)){
            checkinrepo.insertintotable(name, email, phone_num, currentdate, currenttime);
            return ResponseEntity.ok("Yay");
        }
        else{
            return ResponseEntity.badRequest().body("Mohon isi!");
        }
    }

    public List<StaffCheckinDto> displayCheckInRecord(){
        List<StaffCheckIn> entities = checkinrepo.findAllStaff();

        List<StaffCheckinDto> dtoList = new ArrayList<>();

        for (StaffCheckIn s : entities) {
            StaffCheckinDto dto = new StaffCheckinDto(
                    s.getName(),
                    s.getEmail(),
                    s.getPhone_num(),
                    s.getCheckin_date(),
                    s.getCheckin_time()
            );
            dtoList.add(dto);
        }

        return dtoList;
    }
}

