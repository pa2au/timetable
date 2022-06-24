package com.example.timetable.service;

import com.example.timetable.entity.Faculty;
import com.example.timetable.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    @Override
    public List<Faculty> findAllFaculties() {
        return facultyRepository.findAll();
    }
}
