package com.aksharspringboot.controller;

import com.aksharspringboot.dto.CourseDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("admin/course/addCourse")
    public ResponseEntity<Response> addCourse(@RequestBody CourseDto courseDto)
    {
        Response response=this.courseService.addCourse(courseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("admin/course/getAllCourse")
    public ResponseEntity<Response> getAllCourse()
    {
        Response response=this.courseService.getAllCourse();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("admin/course/updateCourse")
    public ResponseEntity<Response> updateCourse(@RequestBody CourseDto courseDto)
    {
        Response response=this.courseService.updateCourse(courseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
