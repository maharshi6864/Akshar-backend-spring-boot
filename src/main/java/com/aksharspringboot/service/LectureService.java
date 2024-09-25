package com.aksharspringboot.service;

import com.aksharspringboot.dto.LectureDto;
import com.aksharspringboot.dto.Response;

public interface LectureService {

    Response startLecture(LectureDto lectureDto);

    Response endLecture(LectureDto lectureDto);
}
