package com.vico.attendance.service;

import com.vico.attendance.repository.StaffRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service

//untuk tgok log
@Slf4j
@AllArgsConstructor
public class StaffService {

    private final StaffRepo staffrepo;

}
