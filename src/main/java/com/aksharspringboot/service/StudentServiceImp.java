package com.aksharspringboot.service;

import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.StudentDto;
import com.aksharspringboot.dto.TeacherDto;
import com.aksharspringboot.model.StudentVo;
import com.aksharspringboot.model.TeacherVo;
import com.aksharspringboot.repository.CourseRepository;
import com.aksharspringboot.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentServiceImp implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Response addStudent(StudentDto studentDto) {
        try {
            StudentVo studentVo = new StudentVo(null, studentDto.getEnrollmentNumber(), studentDto.getFirstName(), studentDto.getLastName(), null, this.courseRepository.findByCourseId(studentDto.getCourseId()).get(0));
            this.studentRepository.save(studentVo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to save student.", null, false);
        }
        return new Response("student saved successfully.", studentDto, true);
    }

    @Override
    public Response getAllStudent() {
        List<StudentVo> studentVoList = new ArrayList<>();
        List<StudentDto> studentDtoList = new ArrayList<>();
        try {
            studentVoList = this.studentRepository.findAll();
            for (StudentVo studentVo : studentVoList) {
                StudentDto studentDto = new StudentDto(studentVo.getId(), studentVo.getEnrollmentNumber(), studentVo.getFirstName(), studentVo.getLastName(), studentVo.getCourseVo().getCourseId(), studentVo.getCourseVo().getCourseName(), studentVo.getCourseVo().getCourseShortName());
                studentDtoList.add(studentDto);
            }

        } catch (Exception e) {
            return new Response("Failed to search Student.", null, false);
        }
        return new Response("Student searched successfully.", Map.of("studentList", studentDtoList), true);

    }

    @Override
    public Response updateStudent(StudentDto studentDto) {
        try {

            List<StudentVo> studentVoList = this.studentRepository.findAllById(studentDto.getId());

            if (studentVoList.isEmpty()) {
                return new Response("Student not found.", null, false);
            }

            StudentVo studentVo= studentVoList.get(0);

            studentVo.setFirstName(studentDto.getFirstName());
            studentVo.setLastName(studentDto.getLastName());
            studentVo.setEnrollmentNumber(studentDto.getEnrollmentNumber());
            studentVo.setCourseVo(this.courseRepository.findByCourseId(studentDto.getCourseId()).get(0));

            this.studentRepository.save(studentVo);

            return new Response("Successfully updated student", studentDto, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to update student.", null, false);
        }
    }
}
