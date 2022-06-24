package com.example.timetable.repository;

import com.example.timetable.entity.WeekDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekDayRepository extends JpaRepository<WeekDay, Integer> {
}
