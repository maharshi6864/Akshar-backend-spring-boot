package com.aksharspringboot.service;

import com.aksharspringboot.dto.BatchDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.SectionDto;

public interface SectionService {

    Response createSection(SectionDto sectionDto);

    Response getAllSection();

    Response updateSection(SectionDto sectionDto);

    Response deleteSection(SectionDto sectionDto);
}
