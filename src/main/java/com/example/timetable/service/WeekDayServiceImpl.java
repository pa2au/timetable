package com.example.timetable.service;

import com.example.timetable.repository.WeekDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeekDayServiceImpl implements com.example.timetable.service.WeekDayService {
    private final WeekDayRepository weekDayRepository;

    @Override
    public List<com.example.timetable.entity.WeekDay> findWeekDayAll() {
        return weekDayRepository.findAll();
    }
}
