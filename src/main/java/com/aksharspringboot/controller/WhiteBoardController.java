package com.aksharspringboot.controller;

import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.WhiteBoardDto;
import com.aksharspringboot.service.WhiteBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhiteBoardController {

    @Autowired
    private WhiteBoardService whiteBoardService;


    @PostMapping("teacher/whiteboard/saveNotes")
    public ResponseEntity<Response> saveNotes(@RequestBody WhiteBoardDto whiteBoardDto)
    {
        Response response=this.whiteBoardService.saveNotes(whiteBoardDto);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }


}
