package com.aksharspringboot.controller;

import com.aksharspringboot.dto.BatchDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.model.BatchVo;
import com.aksharspringboot.model.CourseVo;
import com.aksharspringboot.repository.BatchRepository;
import com.aksharspringboot.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BatchController {

    @Autowired
    private BatchService batchService;

    @Autowired
    private BatchRepository batchRepository;

    @PostMapping("batch/createBatch")
    public ResponseEntity<Response> createBatch(@RequestBody BatchDto batchDto) {
        Response response = this.batchService.createBatch(batchDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("batch/updateBatch")
    public ResponseEntity<Response> updateBatch(@RequestBody BatchDto batchDto) {
        Response response = this.batchService.updateBatch(batchDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("batch/getAllBatch")
    public ResponseEntity<Response> getAllBatch() {
        Response response = this.batchService.getAllCourse();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("batch/deleteBatch")
    public ResponseEntity<Response> deleteBatch(@RequestBody BatchDto batchDto) {
        Response response = this.batchService.deleteBatch(batchDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("batch/getAllBatchByCourse")
    public ResponseEntity<Response> getAllBatchByCourse(@RequestBody CourseVo courseVo) {
        List<BatchVo> batchVoList=this.batchRepository.findByCourseVo(new CourseVo(courseVo.getId(),null,null,null,null));
        Response response=new Response("Batch found by id",batchVoList,true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
