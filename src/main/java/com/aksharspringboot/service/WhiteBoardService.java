package com.aksharspringboot.service;

import com.aksharspringboot.dto.LectureDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.WhiteBoardDto;

public interface WhiteBoardService {

    Response saveNotes(WhiteBoardDto whiteBoardDto);

    Response downloadNotes(LectureDto  lectureDto);
}
