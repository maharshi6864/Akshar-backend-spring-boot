package com.aksharspringboot.controller;

import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.SectionDto;
import com.aksharspringboot.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @PostMapping("admin/section/createSection")
    public ResponseEntity<Response> createSection(@RequestBody SectionDto sectionDto) {
        Response response = this.sectionService.createSection(sectionDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("admin/section/getAllSection")
    public ResponseEntity<Response> getAllSection() {
        Response response = this.sectionService.getAllSection();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("admin/section/updateSection")
    public ResponseEntity<Response> updateSection(@RequestBody SectionDto sectionDto) {
        Response response = this.sectionService.updateSection(sectionDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("admin/section/deleteSection")
    public ResponseEntity<Response> deleteSection(@RequestBody SectionDto sectionDto) {
        Response response = this.sectionService.deleteSection(sectionDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
