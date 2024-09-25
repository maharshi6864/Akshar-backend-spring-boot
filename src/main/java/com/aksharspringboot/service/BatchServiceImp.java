package com.aksharspringboot.service;

import com.aksharspringboot.dto.BatchDto;
import com.aksharspringboot.dto.CourseDto;
import com.aksharspringboot.dto.DepartmentDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.model.BatchVo;
import com.aksharspringboot.model.CourseVo;
import com.aksharspringboot.model.DepartmentVo;
import com.aksharspringboot.repository.BatchRepository;
import com.aksharspringboot.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BatchServiceImp implements BatchService{

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public Response createBatch(BatchDto batchDto) {
        try {
            BatchVo batchVo = new BatchVo(null,batchDto.getBatchName(), batchDto.getStartDate(),
                    batchDto.getEndDate(),this.courseRepository.findByCourseId(batchDto.getCourseId()).get(0));
            this.batchRepository.save(batchVo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed To save batch", null, false);
        }
        return new Response("Batch added", batchDto, true);

    }

    @Override
    public Response getAllCourse() {
        List<BatchVo> batchVoList  = new ArrayList<>();
        List<BatchDto> batchDtoList = new ArrayList<>();
        try {
            batchVoList = this.batchRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to search batches.", null, false);
        }
        for (BatchVo batchVo : batchVoList) {
            CourseVo courseVo=this.courseRepository.findByCourseId(batchVo.getCourseVo().getCourseId()).get(0);
            BatchDto batchDto= new BatchDto(batchVo.getId(), batchVo.getBatchName(),batchVo.getStartData(),batchVo.getEndDate(),courseVo.getCourseId(), courseVo.getCourseName(), courseVo.getCourseShortName());
            batchDtoList.add(batchDto);
        }
        return new Response("Successfully searched batches", Map.of("batch", batchDtoList), true);
    }

    @Override
    public Response updateBatch(BatchDto batchDto) {
        try {

            List<BatchVo> batchVoList = this.batchRepository.findAllById(batchDto.getId());

            if (batchVoList.isEmpty()) {
                return new Response("Batch not found.", null, false);
            }

            BatchVo batchVo = batchVoList.get(0);

            batchVo.setBatchName(batchDto.getBatchName());
            batchVo.setEndDate(batchDto.getStartDate());
            batchVo.setEndDate(batchDto.getEndDate());
            batchVo.setCourseVo(this.courseRepository.findByCourseId(batchDto.getCourseId()).get(0));
            this.batchRepository.save(batchVo);

            return new Response("Successfully updated batch", batchDto, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to update batch.", null, false);
        }
    }

    @Override
    public Response deleteBatch(BatchDto batchDto) {
        try{
            BatchVo batchVo=new BatchVo(batchDto.getId(),null,null,null,null);
            this.batchRepository.delete(batchVo);
            return new Response("Successfully deleted batch", batchDto, true);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new Response("Failed to delete batch", batchDto, true);
        }
    }
}
