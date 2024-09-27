package com.aksharspringboot.service;

import com.aksharspringboot.dto.AttendanceDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.model.AttendanceVo;

public interface AttendanceService {

    Response checkStudentPresenceInClass(AttendanceDto attendanceDto);
}
