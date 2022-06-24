package com.example.timetable.service;

import com.example.timetable.entity.ExerciseTime;
import com.example.timetable.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseTimeServiceImpl implements ExerciseTimeService {
    private final ExerciseRepository exerciseRepository;

    @Override
    public List<ExerciseTime> findExerciseTimeAll() {
        return exerciseRepository.findAll();
    }
}
