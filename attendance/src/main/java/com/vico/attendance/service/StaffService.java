package com.vico.attendance.service;

import com.vico.attendance.repository.StaffRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service

//untuk tgok log
@Slf4j


public class StaffService {

    private final StaffRepo staffrepo;

    public StaffService(StaffRepo staffrepo) {
        this.staffrepo = staffrepo;
    }


}
