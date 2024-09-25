package com.aksharspringboot.service;

import com.aksharspringboot.dto.BatchDto;
import com.aksharspringboot.dto.DepartmentDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.SectionDto;
import com.aksharspringboot.model.BatchVo;
import com.aksharspringboot.model.DepartmentVo;
import com.aksharspringboot.model.SectionVo;
import com.aksharspringboot.repository.BatchRepository;
import com.aksharspringboot.repository.SectionRepository;
import jakarta.transaction.Transactional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SectionServiceImp implements SectionService {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private SectionRepository sectionRepository;


    @Override
    public Response createSection(SectionDto sectionDto) {
        try {
            SectionVo sectionVo = new SectionVo(null, sectionDto.getSectionName(), this.batchRepository.findAllById(sectionDto.getBatchId()).get(0));
            List<SectionVo> sectionVoList = this.sectionRepository.findBySectionNameAndBatchVo(sectionVo.getSectionName(), sectionVo.getBatchVo());
            if (sectionVoList.isEmpty()) {
                this.sectionRepository.save(sectionVo);
                return new Response("Successfully saved section", sectionDto    , true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to save section", null, false);
        }
        return new Response("Failed to save the section duplicate entry found", sectionDto, false);
    }

    @Override
    public Response getAllSection() {
        List<SectionVo> sectionVoList = new ArrayList<>();
        List<SectionDto> sectionDtoList = new ArrayList<>();
        try {
            sectionVoList = this.sectionRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to search departments.", null, false);
        }
        for (SectionVo sectionVo : sectionVoList) {
            SectionDto sectionDto = new SectionDto(sectionVo.getId(), sectionVo.getSectionName(), sectionVo.getBatchVo()
                    .getId(), sectionVo.getBatchVo().getBatchName(), sectionVo.getBatchVo().getStartData(), sectionVo
                    .getBatchVo().getEndDate(), sectionVo.getBatchVo().getCourseVo().getCourseId(),
                    sectionVo.getBatchVo().getCourseVo().getCourseName(),
                    sectionVo.getBatchVo().getCourseVo().getCourseShortName());
            sectionDtoList.add(sectionDto);
        } return new Response("Successfully searched sections", Map.of("sectionList", sectionDtoList), true);
    }

    @Override
    public Response updateSection(SectionDto sectionDto) {
        try {
            // Find the department by departmentId
            List<SectionVo> sectionVoList = this.sectionRepository.findAllById(sectionDto.getId());

            // Check if department is found
            if (sectionVoList.isEmpty()) {
                return new Response("Department not found.", null, false);
            }

            SectionVo sectionVo = sectionVoList.get(0);

            sectionVo.setSectionName(sectionDto.getSectionName());
            sectionVo.setBatchVo(this.batchRepository.findAllById(sectionDto.getBatchId()).get(0));
            this.sectionRepository.save(sectionVo);

            return new Response("Successfully updated section", sectionDto, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to update section.", null, false);
        }
    }


    @Override
    public Response deleteSection(SectionDto sectionDto) {
        try {
            SectionVo sectionVo = new SectionVo( sectionDto.getId(), null, null);
            this.sectionRepository.delete(sectionVo);
            return new Response("Successfully deleted section", sectionDto, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to delete section", sectionDto, true);
        }
    }
}
