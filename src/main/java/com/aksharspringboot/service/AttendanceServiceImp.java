package com.aksharspringboot.service;

import com.aksharspringboot.dto.AttendanceDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.model.ClassRoomVo;
import com.aksharspringboot.model.LectureVo;
import com.aksharspringboot.model.StudentVo;
import com.aksharspringboot.repository.AttendanceRepository;
import com.aksharspringboot.repository.ClassRoomRepository;
import com.aksharspringboot.repository.LectureRepository;
import com.aksharspringboot.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Transactional
public class AttendanceServiceImp implements AttendanceService{

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private GeofenceCheckerService GeofenceCheckerService;

    @Override
    public Response checkStudentPresenceInClass(AttendanceDto attendanceDto) {
        try{
            StudentVo studentVo=this.studentRepository.findAllById(attendanceDto.getStudentId()).get(0);

            LectureVo lectureVo=this.lectureRepository.findAllById(attendanceDto.getLectureId()).get(0);

            ClassRoomVo classRoomVo=this.classRoomRepository.findAllById(lectureVo.getClassRoomVo().getId()).get(0);

            boolean result=this.GeofenceCheckerService.checkPresenseOfStudent(classRoomVo,attendanceDto.getPointVo());

            return new Response("Present In Class Status.", Map.of("presenceStatus",true,"attendanceDto",attendanceDto),result);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new Response("Failed To Present In Class Status.",attendanceDto,false);
        }

    }
}
