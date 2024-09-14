package com.aksharspringboot.service;

import com.aksharspringboot.dto.CourseDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.model.CourseVo;
import com.aksharspringboot.repository.CourseRepository;
import com.aksharspringboot.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CourseServiceImp implements CourseService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Response addCourse(CourseDto courseDto) {
        try {
            CourseVo courseVo = new CourseVo(null, courseDto.getCourseId(), courseDto.getCourseName(),
                    courseDto.getCourseShortName(),
                    this.departmentRepository.findByDepartmentId(courseDto.getDepartmentId()).get(0));
            this.courseRepository.save(courseVo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed To save course", null, false);
        }
        return new Response("Course added", null, true);
    }

    @Override
    public Response getAllCourse() {
        List<CourseVo> courseVoList  = new ArrayList<>();
        List<CourseDto> courseDtoList = new ArrayList<>();
        try {
            courseVoList = this.courseRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to search course.", null, false);
        }
        for (CourseVo courseVo : courseVoList) {
            CourseDto courseDto= new CourseDto(courseVo.getId(), courseVo.getCourseId(), courseVo.getCourseName(),courseVo.getCourseShortName(),courseVo.getDepartmentVo().getDepartmentId(),courseVo.getDepartmentVo().getDepartmentName(),courseVo.getDepartmentVo().getDepartmentShortName());
            courseDtoList.add(courseDto);
        }
        return new Response("Successfully searched course", Map.of("course", courseDtoList), true);
    }

    @Override
    public Response updateCourse(CourseDto courseDto) {
        try {
            // Find the department by departmentId
            List<CourseVo> courseVoList = this.courseRepository.findAllById(courseDto.getId());

            // Check if department is found
            if (courseVoList.isEmpty()) {
                return new Response("Course not found.", null, false);
            }

            CourseVo courseVo = courseVoList.get(0);
            // Update department fields
            courseVo.setCourseId(courseDto.getCourseId());
            courseVo.setCourseName(courseDto.getCourseName());
            courseVo.setCourseShortName(courseDto.getCourseShortName());
            courseVo.setDepartmentVo(this.departmentRepository.findByDepartmentId(courseDto.getDepartmentId()).get(0));
            // Save the updated department
            this.courseRepository.save(courseVo);

            return new Response("Successfully updated course", courseVo, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to update course.", null, false);
        }
    }
}
