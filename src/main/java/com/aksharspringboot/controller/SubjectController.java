package com.aksharspringboot.controller;

import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.SubjectDto;
import com.aksharspringboot.model.CourseVo;
import com.aksharspringboot.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("admin/subject/addSubject")
    public ResponseEntity<Response> addSubject(@RequestBody SubjectDto subjectDto){
        Response response=this.subjectService.addSubject(subjectDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("admin/subject/getSubject")
    public ResponseEntity<Response> getSubject(){
        Response response=this.subjectService.getAllSubject();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("admin/subject/getSubjectByCourseId")
    public ResponseEntity<Response> getSubjectByCourseId(@RequestBody CourseVo courseVo){
        Response response=this.subjectService.getSubjectByCourseId(courseVo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
