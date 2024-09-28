package com.aksharspringboot.service;

import com.aksharspringboot.dto.ClassRoomDto;
import com.aksharspringboot.dto.Response;
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
            ClassRoomVo classRoomVo = this.classRoomRepository.findById(classRoomDto.getId())
                    .orElseThrow(() -> new RuntimeException("ClassRoom not found with id: " + classRoomDto.getId()));

// Update only the longitude and latitude fields
            classRoomVo.setTopRightLongitude(classRoomDto.getTopRightLongitude());
            classRoomVo.setTopRightLatitude(classRoomDto.getTopRightLatitude());
            classRoomVo.setTopLeftLongitude(classRoomDto.getTopLeftLongitude());
            classRoomVo.setTopLeftLatitude(classRoomDto.getTopLeftLatitude());
            classRoomVo.setBottomRightLongitude(classRoomDto.getBottomRightLongitude());
            classRoomVo.setBottomRightLatitude(classRoomDto.getBottomRightLatitude());
            classRoomVo.setBottomLeftLongitude(classRoomDto.getBottomLeftLongitude());
            classRoomVo.setBottomLeftLatitude(classRoomDto.getBottomLeftLatitude());

// Save the updated object back to the repository
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

    @Override
    public Response deleteClassRoom(ClassRoomDto classRoomDto) {
        try{
            ClassRoomVo classRoomVo=new ClassRoomVo(classRoomDto.getId(),null,0,0,0,0,0,0,0,0);
            classRoomRepository.delete(classRoomVo);
            return new Response("Successfully deleted classRoom", classRoomDto, true);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new Response("Failed to delete classRoom", classRoomDto, true);
        }
    }
}
