package com.aksharspringboot.service;

import com.aksharspringboot.dto.BatchDto;
import com.aksharspringboot.dto.Response;

public interface BatchService {

    Response createBatch(BatchDto batchDto);

    Response updateBatch(BatchDto batchDto);

    Response getAllCourse();

    Response deleteBatch(BatchDto batchDto);
}
