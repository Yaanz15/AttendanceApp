package com.vico.attendance.controller;

import com.vico.attendance.dto.StaffCheckinDto;
import com.vico.attendance.entity.Staff;
import com.vico.attendance.entity.StaffCheckIn;
import com.vico.attendance.repository.StaffRepo;
import com.vico.attendance.service.CalendarService;
import com.vico.attendance.service.StaffCheckInService;
import com.vico.attendance.service.StaffService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("api") // starting api call
public class ApiController {

    private final StaffService staffService;
    private final StaffRepo staffrepo;
    private final CalendarService calendarService;
    private final StaffCheckInService staffCheckInService;

    @GetMapping("/getStaffDetail")
    public List<Staff> getStaffDetail() {
        return staffrepo.findAll();
    }

    @GetMapping("/month")
    public ResponseEntity<?> getMonthCalendar(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month
    ) {
        try {
            Map<String, Object> dateResult = calendarService.getMonthCalendar(year, month);
            return ResponseEntity.ok(dateResult);

        } catch (DateTimeException e) {
            log.error("Error while calling service to process date : {}", e.getMessage(), e);

            return ResponseEntity
                    .badRequest()
                    .body("Invalid year, month or day value with message :" + e.getMessage());
        }
    }

    @GetMapping("/findDate")
    public ResponseEntity<?> findDate(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int day
    ) {
        try {

            if (year < 1000 || year > 9999) {
                log.error("Bad input detected in year value");
                return ResponseEntity
                        .badRequest()
                        .body("Invalid year format: must be a 4-digit year");
            }

            LocalDate.of(year, month, day);

            Map<String, Object> response = new HashMap<>();
            response.put("year", year);
            response.put("month", month);
            response.put("day", day); // this is the highlight day
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Bad input detected, returning message : {} ", e.getMessage());
            return ResponseEntity.badRequest().body("Invalid date value : " + e.getMessage());
        }
    }

    @PostMapping("/postStaffCheckIn")
    public ResponseEntity<?> checkin (String name, String email, Long phone_num){
        return staffCheckInService.staffCheckIn(name, email, phone_num);
    }

    @GetMapping("/getStaffCheckIn")
    public ResponseEntity<List<StaffCheckinDto>> getStaffCheckInRecords(){
        return ResponseEntity.ok(staffCheckInService.displayCheckInRecord());
    }
}

