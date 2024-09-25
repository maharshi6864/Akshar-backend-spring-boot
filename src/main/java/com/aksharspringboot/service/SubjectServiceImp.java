package com.aksharspringboot.service;

import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.SubjectDto;
import com.aksharspringboot.model.ChapterVo;
import com.aksharspringboot.model.CourseVo;
import com.aksharspringboot.model.SubjectVo;
import com.aksharspringboot.repository.ChapterRepository;
import com.aksharspringboot.repository.CourseRepository;
import com.aksharspringboot.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SubjectServiceImp implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public Response addSubject(SubjectDto subjectDto) {
        SubjectVo subjectVo = new SubjectVo(null, subjectDto.getSubjectName(), new CourseVo(subjectDto.getCourseId(), null, null, null
                , null));
        try {
            SubjectVo savedSubjectVo = this.subjectRepository.save(subjectVo);
            for (ChapterVo chapterVo : subjectDto.getChapterVoList()) {
                chapterVo.setSubjectVo(savedSubjectVo);
                this.chapterRepository.save(chapterVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to save subject", null, false);
        }
        return new Response("Successfully saved subject", subjectVo, true);
    }

    @Override
    public Response getAllSubject() {
        try {
            List<SubjectVo> subjectVoList;
            List<SubjectDto> subjectDtoList = new ArrayList<>();

            subjectVoList = this.subjectRepository.findAll();

            for (SubjectVo subjectVo : subjectVoList) {
                CourseVo courseVo = this.courseRepository.findAllById(subjectVo.getCourseVo().getId()).get(0);
                List<ChapterVo> chapterVoList = this.chapterRepository.findBySubjectVo(subjectVo);
                SubjectDto subjectDto = new SubjectDto(subjectVo.getId(), subjectVo.getSubjectName()
                        , chapterVoList, courseVo.getId(), courseVo.getCourseName(), courseVo.getCourseShortName());
                subjectDtoList.add(subjectDto);
            }
            return new Response("Successfully searched subject", Map.of("subjectList", subjectDtoList), true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to search departments.", null, false);
        }

    }

    @Override
    public Response getSubjectByCourseId(CourseVo courseVo) {
        try {
            List<SubjectVo> subjectVoList = new ArrayList<>();
            List<SubjectDto> subjectDtoList = new ArrayList<>();

            subjectVoList = this.subjectRepository.findSubjectVoByCourseVo(courseVo);

            for (SubjectVo subjectVo : subjectVoList) {
                courseVo = this.courseRepository.findAllById(subjectVo.getCourseVo().getId()).get(0);
                List<ChapterVo> chapterVoList = this.chapterRepository.findBySubjectVo(subjectVo);
                SubjectDto subjectDto = new SubjectDto(subjectVo.getId(), subjectVo.getSubjectName()
                        , chapterVoList, courseVo.getId(), courseVo.getCourseName(), courseVo.getCourseShortName());
                subjectDtoList.add(subjectDto);
            }
            return new Response("Successfully searched subject", Map.of("subjectList", subjectDtoList), true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to search departments.", null, false);
        }
    }
}