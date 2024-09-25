package com.aksharspringboot.repository;

import com.aksharspringboot.model.BatchVo;
import com.aksharspringboot.model.DepartmentVo;
import com.aksharspringboot.model.SectionVo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SectionRepository extends MongoRepository<SectionVo, ObjectId> {

    List<SectionVo> findByBatchVo(BatchVo batchVo);

    List<SectionVo> findAllById(ObjectId id);

    List<SectionVo> findBySectionNameAndBatchVo(String sectionName, BatchVo batchVo);
}

