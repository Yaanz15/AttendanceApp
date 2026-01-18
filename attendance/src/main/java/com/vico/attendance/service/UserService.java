package com.vico.attendance.service;

import com.vico.attendance.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public String getStaffNameById(Long staffId) {
        return userRepo.findNameById(staffId);
    }
}
