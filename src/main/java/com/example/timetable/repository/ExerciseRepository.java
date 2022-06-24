package com.example.timetable.repository;

import com.example.timetable.entity.ExerciseTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseTime, Integer> {
}
