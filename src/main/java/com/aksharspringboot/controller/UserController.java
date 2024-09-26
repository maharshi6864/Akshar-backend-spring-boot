package com.aksharspringboot.controller;

import com.aksharspringboot.dto.Response;
import com.aksharspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("user/getUserDetails")
    public ResponseEntity<Response> getUserDetails()
    {
        Response response=this.userService.getUserDetails();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
