package com.aksharspringboot.service;

import com.aksharspringboot.dto.LectureDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.model.LectureVo;
import com.aksharspringboot.model.SectionVo;
import com.aksharspringboot.model.TeacherVo;
import com.aksharspringboot.repository.LectureRepository;
import com.aksharspringboot.repository.SectionRepository;
import com.aksharspringboot.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class LectureServiceImp implements LectureService{

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SectionRepository sectionRepository;


    @Override
    public Response startLecture(LectureDto lectureDto) {
        LectureVo lectureVo=new LectureVo(null,lectureDto.getLectureTime(), System.currentTimeMillis(),
                0,this.sectionRepository.findAllById(lectureDto.getSectionId()).get(0),
                this.teacherRepository.findAllById(lectureDto.getTeacherId()).get(0));
        return new Response("New Lecture Created",lectureVo,true);
    }

    @Override
    public Response endLecture(LectureDto lectureDto) {
        return null;
    }
}
