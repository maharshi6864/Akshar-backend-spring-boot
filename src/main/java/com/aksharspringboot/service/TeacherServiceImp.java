package com.aksharspringboot.service;

import com.aksharspringboot.dto.DepartmentDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.TeacherDto;
import com.aksharspringboot.model.DepartmentVo;
import com.aksharspringboot.model.TeacherVo;
import com.aksharspringboot.model.UserVo;
import com.aksharspringboot.repository.DepartmentRepository;
import com.aksharspringboot.repository.TeacherRepository;
import com.aksharspringboot.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TeacherServiceImp implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Response addTeacher(TeacherDto teacherDto) {
        try {
            UserVo userVo=new UserVo(null,teacherDto.getTeacherEmailAddress(),null,"TEACHER",
                    true,1);
            userVo.setPassword(passwordEncoder.encode("teacher"));
            UserVo savedUserVo=this.userRepository.save(userVo);
            TeacherVo teacherVo = new TeacherVo(null, teacherDto.getTeacherId(), teacherDto.getFirstName(),
                    teacherDto.getLastName(), savedUserVo,
                    this.departmentRepository.findByDepartmentId(teacherDto.getDepartmentId()).get(0));
            this.teacherRepository.save(teacherVo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to save teacher.", null, false);
        }
        return new Response("Teacher saved successfully.", teacherDto, true);
    }

    @Override
    public Response getAllTeacher() {
        List<TeacherVo> teacherVoList = new ArrayList<>();
        List<TeacherDto> teacherDtoList = new ArrayList<>();
        try {
            teacherVoList = this.teacherRepository.findAll();
            for (TeacherVo teacherVo : teacherVoList) {
                TeacherDto teacherDto = new TeacherDto(teacherVo.getId(), teacherVo.getTeacherId(),
                        this.userRepository.findAllById(teacherVo.getUserVo().getId()).get(0).getUsername(),
                        teacherVo.getFirstName(), teacherVo.getLastName(), teacherVo.getDepartmentVo().getDepartmentId(),
                        teacherVo.getDepartmentVo().getDepartmentName(),
                        teacherVo.getDepartmentVo().getDepartmentShortName());
                teacherDtoList.add(teacherDto);
            }

        } catch (Exception e) {
            return new Response("Failed to search Teacher.", null, false);
        }
        return new Response("Teacher searched successfully.", Map.of("teacherList", teacherDtoList), true);
    }

    @Override
    public Response updateTeacher(TeacherDto teacherDto) {
        try {

            List<TeacherVo> teacherVoList = this.teacherRepository.findAllById(teacherDto.getId());

            if (teacherVoList.isEmpty()) {
                return new Response("Teacher not found.", null, false);
            }

            TeacherVo teacherVo = teacherVoList.get(0);
            UserVo userVo=this.userRepository.findAllById(teacherVo.getUserVo().getId()).get(0);
            teacherVo.setTeacherId(teacherDto.getTeacherId());
            teacherVo.setFirstName(teacherDto.getFirstName());
            teacherVo.setLastName(teacherDto.getLastName());
            userVo.setUsername(teacherDto.getTeacherEmailAddress());
            teacherVo.setDepartmentVo(this.departmentRepository.findByDepartmentId(teacherDto.getDepartmentId()).get(0));
            this.userRepository.save(userVo);
            this.teacherRepository.save(teacherVo);
            return new Response("Successfully updated teacher", teacherVo, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to update teacher.", null, false);
        }
    }

    @Override
    public Response deleteTeacher(TeacherDto teacherDto) {
        try{
            TeacherVo teacherVo=new TeacherVo(teacherDto.getId(),teacherDto.getTeacherId(),null,null,null,null);
            teacherRepository.delete(teacherVo);
            return new Response("Successfully deleted teacher", teacherDto, true);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new Response("Failed to delete teacher", teacherDto, true);
        }
    }

    @Override
    public Response getAllTeacherForDepartment(DepartmentDto departmentDto) {
        List<TeacherVo> teacherVoList = new ArrayList<>();
        List<TeacherDto> teacherDtoList = new ArrayList<>();
        try {
            DepartmentVo departmentVo=new DepartmentVo(departmentDto.getId(),departmentDto.getDepartmentId(),
                    null,null,null);
            teacherVoList = this.teacherRepository.findByDepartmentVo(departmentVo);
            for (TeacherVo teacherVo : teacherVoList) {
                TeacherDto teacherDto = new TeacherDto(teacherVo.getId(), teacherVo.getTeacherId(),
                        teacherVo.getFirstName(),
                        this.userRepository.findAllById(teacherVo.getUserVo().getId()).get(0).getUsername(),teacherVo.getLastName(),
                        teacherVo.getDepartmentVo().getDepartmentId(), teacherVo.getDepartmentVo().getDepartmentName(),
                        teacherVo.getDepartmentVo().getDepartmentShortName());
                teacherDtoList.add(teacherDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to search Teacher by departmentId.", null, false);
        }
        return new Response("Teacher searched by departmentId successfully.", Map.of("teacherList", teacherDtoList), true);
    }
}
