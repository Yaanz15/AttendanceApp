package com.vico.attendance.controller;

import com.vico.attendance.dto.StaffCheckinDto;
import com.vico.attendance.entity.Staff;
import com.vico.attendance.entity.StaffCheckIn;
import com.vico.attendance.repository.StaffRepo;
import com.vico.attendance.service.CalendarService;
import com.vico.attendance.service.StaffCheckInService;
import com.vico.attendance.service.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
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
    public Map<String, Object> getMonthCalendar(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month
    ) {
        return calendarService.getMonthCalendar(year, month);
    }

    @GetMapping("/findDate")
    public Map<String, Object> findDate(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int day
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put("year", year);
        response.put("month", month);
        response.put("day", day); // this is the highlight day
        return response;
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

