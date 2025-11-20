package com.vico.attendance.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Setter
@Getter
@Table(name="staffcheckin")

public class StaffCheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Long phone_num;
    private LocalDate checkin_date;
    private LocalTime checkin_time;

}
