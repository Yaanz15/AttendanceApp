package com.vico.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class StaffCheckinDto {
    private String name;
    private String email;
    private Long phone_num;
    private LocalDate checkin_date;
    private LocalTime checkin_time;
}
