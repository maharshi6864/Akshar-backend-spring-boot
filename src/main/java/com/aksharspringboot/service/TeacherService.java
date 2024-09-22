package com.aksharspringboot.service;

import com.aksharspringboot.dto.DepartmentDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.TeacherDto;

public interface TeacherService {

    Response addTeacher(TeacherDto teacherDto);

    Response getAllTeacher();

    Response updateTeacher(TeacherDto teacherDto);

    Response deleteTeacher(TeacherDto teacherDto);

    Response getAllTeacherForDepartment(DepartmentDto departmentDto);
}
