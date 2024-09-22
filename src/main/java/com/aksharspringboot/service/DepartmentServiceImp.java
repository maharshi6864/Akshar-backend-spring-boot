package com.aksharspringboot.service;

import com.aksharspringboot.dto.DepartmentDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.model.DepartmentVo;
import com.aksharspringboot.model.TeacherVo;
import com.aksharspringboot.repository.CourseRepository;
import com.aksharspringboot.repository.DepartmentRepository;
import com.aksharspringboot.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DepartmentServiceImp implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Response addDepartment(DepartmentDto departmentDto) {
        DepartmentVo departmentVo = new DepartmentVo(null, departmentDto.getDepartmentId(),
                departmentDto.getDepartmentName(), departmentDto.getDepartmentShortName(),null);
        try {
            this.departmentRepository.save(departmentVo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to save department", null, false);
        }
        return new Response("Successfully saved department", departmentVo, true);
    }

    @Override
    public Response getAllDepartment() {
        List<DepartmentVo> departmentVoList = new ArrayList<>();
        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        try {
            departmentVoList = this.departmentRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to search departments.", null, false);
        }
        for (DepartmentVo departmentVo : departmentVoList) {
            TeacherVo teacherVo=departmentVo.getTeacherVo();
            if (teacherVo==null)
            {
                teacherVo=new TeacherVo();
                teacherVo.setFirstName("yet not assigned");
            }
            DepartmentDto departmentDto = new DepartmentDto(departmentVo.getId(), departmentVo.getDepartmentId(),
                    departmentVo.getDepartmentName(), departmentVo.getDepartmentShortName(),teacherVo,this.courseRepository.findByDepartmentVo(departmentVo).size(),this.teacherRepository.findByDepartmentVo(departmentVo).size());
            departmentDtoList.add(departmentDto);
        }
        return new Response("Successfully searched department", Map.of("departmentList", departmentDtoList), true);
    }

    @Override
    public Response updateDepartment(DepartmentDto departmentDto) {
        try {
            // Find the department by departmentId
            List<DepartmentVo> departmentVoList = this.departmentRepository.findAllById(departmentDto.getId());

            // Check if department is found
            if (departmentVoList.isEmpty()) {
                return new Response("Department not found.", null, false);
            }

            DepartmentVo departmentVo = departmentVoList.get(0);
            // Update department fields
            departmentVo.setDepartmentId(departmentDto.getDepartmentId());
            departmentVo.setDepartmentName(departmentDto.getDepartmentName());
            departmentVo.setDepartmentShortName(departmentDto.getDepartmentShortName());

            // Save the updated department
            this.departmentRepository.save(departmentVo);

            return new Response("Successfully updated department", departmentVo, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to update department.", null, false);
        }
    }

    @Override
    public Response checkForDepartmentIdAvail(DepartmentDto departmentDto) {
        List<DepartmentVo> departmentVoList = new ArrayList<>();
        try {
            departmentVoList = this.departmentRepository.findByDepartmentId(departmentDto.getDepartmentId());
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to search department by id.", null, false);
        }
        if (departmentVoList.isEmpty())
        {
            return new Response("Successfully searched department", "Department id available", true);
        }
        return new Response("Successfully searched department", "DepartmentId is not available", false);
    }

}
