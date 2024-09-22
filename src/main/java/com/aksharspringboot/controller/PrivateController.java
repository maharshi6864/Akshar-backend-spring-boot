package com.aksharspringboot.controller;

import com.aksharspringboot.dto.Response;
import com.aksharspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {

    @Autowired
    private UserService userService;

    @PostMapping("admin/loadUsername")
    public ResponseEntity<Response> loadUsername()
    {
        return new ResponseEntity<>(new Response("success",userService.getCurrentUser().getUsername(),true),HttpStatus.OK);
    }


}
