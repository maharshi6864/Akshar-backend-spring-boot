package com.aksharspringboot.service;

import com.aksharspringboot.dto.ClassRoomDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.model.BatchVo;
import com.aksharspringboot.model.ClassRoomVo;
import com.aksharspringboot.model.DepartmentVo;
import com.aksharspringboot.repository.ClassRoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ClassRoomServiceImp implements ClassRoomService{

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Override
    public Response addClassRoom(ClassRoomDto classRoomDto)
    {
        try {
            ClassRoomVo classRoomVo = new ClassRoomVo(null,classRoomDto.getClassRoomNumber(),0,
                    0,0,0,0,0,
                    0,0);
            ClassRoomVo savedClassRoomVo =this.classRoomRepository.save(classRoomVo);
            classRoomDto.setId(savedClassRoomVo.getId());
            return new Response("Class room added successfully", classRoomDto, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed To save classRoom", classRoomDto, false);
        }

    }

    @Override
    public Response addClassRoomMapping(ClassRoomDto classRoomDto)
    {
        try {
            ClassRoomVo classRoomVo = new ClassRoomVo(classRoomDto.getId(),classRoomDto.getClassRoomNumber(),classRoomDto.getTopRightLongitude(),
                    classRoomDto.getTopRightLatitude(), classRoomDto.getTopLeftLongitude(),classRoomDto.getTopLeftLatitude(),
                    classRoomDto.getBottomRightLongitude(),classRoomDto.getBottomRightLatitude(),
                    classRoomDto.getBottomLeftLongitude(),classRoomDto.getBottomLeftLatitude());
            this.classRoomRepository.save(classRoomVo);
            return new Response("Class room mapped successfully", classRoomDto, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed To save classRoom mapping", classRoomDto, false);
        }

    }

    @Override
    public Response getClassRoom() {
        List<ClassRoomVo> classRoomVoList = new ArrayList<>();
        List<ClassRoomDto> clasDepartmentDtoList = new ArrayList<>();
        try {
            classRoomVoList = this.classRoomRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to search class rooms.", null, false);
        }
        for (ClassRoomVo classRoomVo : classRoomVoList) {
            ClassRoomDto classRoomDto = new ClassRoomDto(classRoomVo.getId(),classRoomVo.getClassRoomNumber(),classRoomVo.getTopRightLongitude(),classRoomVo.getTopRightLatitude(),classRoomVo.getTopLeftLongitude(),classRoomVo.getTopLeftLatitude(),classRoomVo.getBottomRightLongitude(),classRoomVo.getBottomRightLatitude(),classRoomVo.getBottomLeftLongitude(),classRoomVo.getBottomLeftLongitude());
            clasDepartmentDtoList.add(classRoomDto);
        }
        return new Response("Successfully searched department", Map.of("classRoomList", clasDepartmentDtoList), true);
    }

    @Override
    public Response getClassRoomById(ClassRoomDto classRoomDto) {
        try {
            // Find the department by departmentId
            ClassRoomVo classRoomVo = this.classRoomRepository.findAllById(classRoomDto.getId()).get(0);

            classRoomDto = new ClassRoomDto(classRoomVo.getId(),classRoomVo.getClassRoomNumber(),classRoomVo.getTopRightLongitude(),classRoomVo.getTopRightLatitude(),classRoomVo.getTopLeftLongitude(),classRoomVo.getTopLeftLatitude(),classRoomVo.getBottomRightLongitude(),classRoomVo.getBottomRightLatitude(),classRoomVo.getBottomLeftLongitude(),classRoomVo.getBottomLeftLongitude());

            return new Response("Classroom found", classRoomDto, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to found classroom.", null, false);
        }
    }
}
