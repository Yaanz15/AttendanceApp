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

    public Map<String, Object> getMonthCalendar(Integer year, Integer month) {

        LocalDate today = LocalDate.now();

        int y = (year != null) ? year : today.getYear();
        int m = (month != null) ? month : today.getMonthValue();

        LocalDate firstDay = LocalDate.of(y, m, 1);
        int lengthOfMonth = firstDay.lengthOfMonth();
        int startDayOfWeek = firstDay.getDayOfWeek().getValue(); // 1 = Monday, 7 = Sunday

        Map<String, Object> data = new HashMap<>();
        data.put("year", y);
        data.put("month", m);
        data.put("daysInMonth", lengthOfMonth);
        data.put("startDayOfWeek", startDayOfWeek);

        return data;
    }

}
