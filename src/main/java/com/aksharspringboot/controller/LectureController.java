package com.aksharspringboot.controller;

import com.aksharspringboot.dto.AttendanceDto;
import com.aksharspringboot.dto.LectureDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.model.StudentVo;
import com.aksharspringboot.model.TeacherVo;
import com.aksharspringboot.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LectureController {

    @Autowired
    private LectureService lectureService;

    @PostMapping("teacher/lecture/startLecture")
    public ResponseEntity<Response> createLecture(@RequestBody LectureDto lectureDto)
    {
        return new ResponseEntity<>(this.lectureService.startLecture(lectureDto), HttpStatus.OK);
    }

    @PostMapping("teacher/lecture/getLecturesByTeacherId")
    public ResponseEntity<Response> getLecturesByTeacherId(@RequestBody TeacherVo teacherVo)
    {
        Response response=this.lectureService.getLecturesByTeacherId(teacherVo);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("student/lecture/getLecturesByStudentId")
    public ResponseEntity<Response> getLecturesByStudentId(@RequestBody StudentVo studentVo)
    {
        Response response=this.lectureService.getLecturesByStudentId(studentVo);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}