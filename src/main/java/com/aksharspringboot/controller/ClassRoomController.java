package com.aksharspringboot.controller;

import com.aksharspringboot.dto.ClassRoomDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassRoomController {

    @Autowired
    private ClassRoomService classRoomService;

    @GetMapping("admin/classRoom/getAllClassRoom")
    public ResponseEntity<Response> getClassRoom()
    {
        Response response=this.classRoomService.getClassRoom();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("admin/classRoom/getClassRoomById")
    public ResponseEntity<Response> getClassRoomById(@RequestBody ClassRoomDto classRoomDto)
    {
        Response response=this.classRoomService.getClassRoomById(classRoomDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("admin/classRoom/addClassRoom")
    public ResponseEntity<Response> addClassRoom(@RequestBody ClassRoomDto classRoomDto)
    {
        Response response=this.classRoomService.addClassRoom(classRoomDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("admin/classRoom/mapClassRoom")
    public ResponseEntity<Response> mapClassRoom(@RequestBody ClassRoomDto classRoomDto)
    {
        Response response=this.classRoomService.addClassRoomMapping(classRoomDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("admin/classRoom/deleteClassRoom")
    public ResponseEntity<Response> deleteClassRoom(@RequestBody ClassRoomDto classRoomDto)
    {
        Response response=this.classRoomService.deleteClassRoom(classRoomDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("teacher/classRoom/getAllClassRoom")
    public ResponseEntity<Response> getClassRoomForTeacher()
    {
        Response response=this.classRoomService.getClassRoom();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
