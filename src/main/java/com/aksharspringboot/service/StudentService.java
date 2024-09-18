package com.aksharspringboot.service;

import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.StudentDto;

public interface StudentService {

    Response addStudent(StudentDto studentDto);

    Response getAllStudent();

    Response updateStudent(StudentDto studentDto);
}
