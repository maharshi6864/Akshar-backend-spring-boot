package com.aksharspringboot.service;

import com.aksharspringboot.dto.CourseDto;
import com.aksharspringboot.dto.Response;

public interface CourseService {

    Response addCourse(CourseDto courseDto);

    Response getAllCourse();

    Response updateCourse(CourseDto courseDto);
}
