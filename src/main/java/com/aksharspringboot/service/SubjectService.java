package com.aksharspringboot.service;

import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.SubjectDto;
import com.aksharspringboot.model.CourseVo;

public interface SubjectService {

    Response addSubject(SubjectDto subjectDto);

    Response getAllSubject();

    Response getSubjectByCourseId(CourseVo courseVo);
}
