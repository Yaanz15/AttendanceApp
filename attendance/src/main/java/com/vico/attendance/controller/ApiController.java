package com.vico.attendance.controller;

import com.vico.attendance.entity.Staff;
import com.vico.attendance.repository.StaffRepo;
import com.vico.attendance.service.StaffService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/dashboard") // starting api call
public class ApiController {

    private final StaffService staffService;
    private final StaffRepo staffrepo;

    public ApiController(StaffService staffService, StaffRepo staffrepo) {
        this.staffService = staffService;
        this.staffrepo = staffrepo;
    }

    @GetMapping("/getStaffDetail")
    public List<Staff> getStaffDetail() {
        return staffrepo.findAll();
    }


}

