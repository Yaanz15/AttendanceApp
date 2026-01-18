package com.vico.attendance.controller;

import com.vico.attendance.dto.StaffCheckinDto;
import com.vico.attendance.entity.Staff;
import com.vico.attendance.entity.StaffCheckIn;
import com.vico.attendance.entity.User;
import com.vico.attendance.repository.StaffRepo;
import com.vico.attendance.repository.UserRepo;
import com.vico.attendance.service.CalendarService;
import com.vico.attendance.service.StaffCheckInService;
import com.vico.attendance.service.StaffService;
import com.vico.attendance.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("api") // starting api call
public class ApiController {

    private final StaffService staffService;
    private final UserService userService;
    private final StaffRepo staffrepo;
    private final UserRepo userRepo;
    private final CalendarService calendarService;
    private final StaffCheckInService staffCheckInService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {

        User user = userRepo.findByEmail(email);

        if (ObjectUtils.isEmpty(user)) {
            return ResponseEntity.status(401).body("User not found");
        }

        // SIMPLE password not match
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Invalid password");
        }

        // Store logged-in user
        session.setAttribute("staffId", user.getUserId());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout (HttpSession httpSession) {
        httpSession.invalidate();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getStaffDetail")
    public ResponseEntity<Staff> getStaffDetail(HttpSession httpSession) {

        Long staffId = (Long) httpSession.getAttribute("staffId");

        if (ObjectUtils.isEmpty(staffId)) {
            log.warn("User is unauthorized for staffId ({})", staffId);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Staff> staffDetail = staffrepo.findById(staffId);

        if (staffDetail.isPresent()) {
            Staff staff = staffDetail.get();
            return ResponseEntity.ok(staff);
        }

        log.error("No staff detail found for staffId ({})", staffId);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getStaffImage")
    public ResponseEntity<String> getMyProfileImage(HttpSession session) {

        Long staffId = (Long) session.getAttribute("staffId");

        if (ObjectUtils.isEmpty(staffId)) {
            log.warn("Unauthorized user for staffId ({})", staffId);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Staff> staffOptional = staffrepo.findById(staffId);

        if (staffOptional.isEmpty()) {
            log.error("Staff info not found for staffId ({})", staffId);
            return ResponseEntity.notFound().build();
        }

        String imagePath = staffOptional.get().getProfile_image();

        if (ObjectUtils.isEmpty(imagePath)) {
            log.error("Staff image not found for staffId ({})", staffId);
            return ResponseEntity.ok("/images/default-avatar.jpg");
        }

        return ResponseEntity.ok(imagePath);
    }


    @GetMapping("/staffName")
    public String getMyName(HttpSession session) {

        Long staffId = (Long) session.getAttribute("staffId");

        if (staffId == null) {
            return "";
        }

        return userService.getStaffNameById(staffId);
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

