package com.aksharspringboot.controller;

import com.aksharspringboot.dto.TeacherDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck()
    {
        return  new ResponseEntity<String>("ADMIN HERE.", HttpStatus.OK);
    }

    @GetMapping("/register-teacher")
    public ResponseEntity<String> registerTeacher(@RequestBody TeacherDto teacherDto)
    {
        return new ResponseEntity<>("Teacher here.", HttpStatus.OK);
    }
}
