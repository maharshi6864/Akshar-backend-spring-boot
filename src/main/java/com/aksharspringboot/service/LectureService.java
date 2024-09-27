package com.aksharspringboot.service;

import com.aksharspringboot.dto.LectureDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.model.StudentVo;
import com.aksharspringboot.model.TeacherVo;

public interface LectureService {

    Response startLecture(LectureDto lectureDto);

    Response endLecture(LectureDto lectureDto);

    Response getLecturesByTeacherId(TeacherVo teacherVo);

    Response getLecturesByStudentId(StudentVo studentVo);
}
