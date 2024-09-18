package com.aksharspringboot.controller;

import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.TeacherDto;
import com.aksharspringboot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("admin/teacher/addTeacher")
    public ResponseEntity<Response> addTeacher(@RequestBody TeacherDto teacherDto)
    {
        Response response=this.teacherService.addTeacher(teacherDto);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @GetMapping("admin/teacher/getAllTeacher")
    public ResponseEntity<Response> getAllTeacher()
    {
        Response response=this.teacherService.getAllTeacher();
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @PostMapping("admin/teacher/updateTeacher")
    public ResponseEntity<Response> updateTeacher(@RequestBody TeacherDto teacherDto)
    {
        Response response=this.teacherService.updateTeacher(teacherDto);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @PostMapping("admin/teacher/deleteTeacher")
    public ResponseEntity<Response> deleteTeacher(@RequestBody TeacherDto teacherDto)
    {
        Response response=this.teacherService.deleteTeacher(teacherDto);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
}
