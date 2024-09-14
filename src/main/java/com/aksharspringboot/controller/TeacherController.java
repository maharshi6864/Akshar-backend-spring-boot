package com.aksharspringboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/teacher")
public class TeacherController {

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck()
    {
        return new ResponseEntity<>("Teacher here.", HttpStatus.OK);
    }

//    @GetMapping("/register")
//    public ResponseEntity<String> healthCheck()
//    {
//        return new ResponseEntity<>("Teacher here.", HttpStatus.OK);
//    }
}
