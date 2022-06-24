package com.example.timetable.service;

import com.example.timetable.entity.Group;

import java.util.List;

public interface GroupService {
    List<Group> findAllGroups();

    void save(int facultyId, String name);

    void delete(int id);

    Group findGroupById(int id);

    void updateGroup(int id, int facultyId, String name);

}
