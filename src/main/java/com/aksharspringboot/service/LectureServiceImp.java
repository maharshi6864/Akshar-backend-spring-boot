package com.aksharspringboot.service;

import com.aksharspringboot.dto.LectureDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.model.*;
import com.aksharspringboot.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LectureServiceImp implements LectureService{

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;


    @Override
    public Response startLecture(LectureDto lectureDto) {
        CourseVo courseVo=new CourseVo(lectureDto.getCourseId(),null,null,null,null);
        BatchVo batchVo=new BatchVo(lectureDto.getBatchId(),null,null,null,null,null);
        SectionVo sectionVo=new SectionVo(lectureDto.getSectionId(),null,null);
        TeacherVo teacherVo=new TeacherVo(lectureDto.getTeacherId(),null,null,null,null,null);
        ClassRoomVo classRoomVo=new ClassRoomVo(lectureDto.getClassRoomId(),null,0,0,0,0,0,0,0,0);
        LectureVo lectureVo=new LectureVo(null,lectureDto.getLectureTime(), System.currentTimeMillis(),
                0,true,courseVo,batchVo,sectionVo,teacherVo,classRoomVo);
        try
        {
            LectureVo savedLectureVo=this.lectureRepository.save(lectureVo);
            lectureDto.setId(savedLectureVo.getId());
            return new Response("New Lecture Created",lectureDto,true);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new Response("Failed to start lecture.", lectureDto, false);
        }
    }

    @Override
    public Response endLecture(LectureDto lectureDto) {
        try
        {
            LectureVo lectureVo=this.lectureRepository.findAllById(lectureDto.getId()).get(0);
            lectureVo.setLectureStatus(false);
            lectureVo.setLectureEndTimeStamp(System.currentTimeMillis());
            LectureVo savedLectureVo=this.lectureRepository.save(lectureVo);
            lectureDto.setId(savedLectureVo.getId());
            return new Response("Lecture Ended SuccessFully",lectureDto,true);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new Response("Failed to end the lecture.", lectureDto, false);
        }
    }

    @Override
    public Response getLecturesByTeacherId(TeacherVo teacherVo) {
        try{
            List<LectureVo> lectureVoList=this.lectureRepository.findByTeacherVo(teacherVo);
            List<LectureDto> lectureDtoList=new ArrayList<>();

            for (LectureVo lectureVo : lectureVoList) {
                CourseVo courseVo=this.courseRepository.findAllById(lectureVo.getCourseVo().getId()).get(0);
                BatchVo batchVo=this.batchRepository.findAllById(lectureVo.getBatchVo().getId()).get(0);
                SectionVo sectionVo=this.sectionRepository.findAllById(lectureVo.getSectionVo().getId()).get(0);
                ClassRoomVo classRoomVo=this.classRoomRepository.findAllById(lectureVo.getClassRoomVo().getId()).get(0);
//                TeacherVo teacherVo1=this.teacherRepository.findAllById(lectureVo.getTeacherVo().getId()).get(0);

                LectureDto lectureDto= new LectureDto(lectureVo.getId(),lectureVo.getLectureActualTimings(),
                        batchVo.getBatchName(),sectionVo.getSectionName(),courseVo.getCourseName(),classRoomVo.getClassRoomNumber(),
                        lectureVo.getLectureStartTimeStamp(), lectureVo.getLectureEndTimeStamp(),null,
                        null,null,null,null);
                lectureDtoList.add(lectureDto);
            }
            return new Response("Successfully searched lecture For Teacher", Map.of("lectureDto", lectureDtoList), true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to search lectures for teacher.", null, false);
        }
    }

    @Override
    public Response getLecturesByStudentId(StudentVo studentVo) {
        try{
            StudentVo studentVo1=this.studentRepository.findAllById(studentVo.getId()).get(0);
            SectionVo sectionVo=this.sectionRepository.findAllById(studentVo1.getSectionVo().getId()).get(0);
            List<LectureVo> lectureVoList=this.lectureRepository.findBySectionVo(sectionVo);
            List<LectureDto> lectureDtoList=new ArrayList<>();

            for (LectureVo lectureVo : lectureVoList) {
                CourseVo courseVo=this.courseRepository.findAllById(lectureVo.getCourseVo().getId()).get(0);
                BatchVo batchVo=this.batchRepository.findAllById(lectureVo.getBatchVo().getId()).get(0);
                ClassRoomVo classRoomVo=this.classRoomRepository.findAllById(lectureVo.getClassRoomVo().getId()).get(0);
//                SectionVo sectionVo=this.sectionRepository.findAllById(lectureVo.getSectionVo().getId()).get(0);
//                TeacherVo teacherVo1=this.teacherRepository.findAllById(lectureVo.getTeacherVo().getId()).get(0);

                LectureDto lectureDto= new LectureDto(lectureVo.getId(),lectureVo.getLectureActualTimings(),
                        batchVo.getBatchName(),sectionVo.getSectionName(),courseVo.getCourseName(),classRoomVo.getClassRoomNumber(),
                        lectureVo.getLectureStartTimeStamp(), lectureVo.getLectureEndTimeStamp(),null,null,null,null,null);
                lectureDtoList.add(lectureDto);
            }
            return new Response("Successfully searched lecture For Student", Map.of("lectureDto", lectureDtoList), true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to search lectures for Student.", null, false);
        }
    }
}
