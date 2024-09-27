package com.aksharspringboot.controller;

import com.aksharspringboot.dto.AttendanceDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.model.AttendanceVo;
import com.aksharspringboot.model.StudentVo;
import com.aksharspringboot.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("student/attendance/checkUser")
    public ResponseEntity<Response> checkStudentPresenceInClass(@RequestBody AttendanceDto attendanceDto){
        Response response=this.attendanceService.checkStudentPresenceInClass(attendanceDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
