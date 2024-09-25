package com.aksharspringboot.repository;

import com.aksharspringboot.model.BatchVo;
import com.aksharspringboot.model.CourseVo;
import com.aksharspringboot.model.DepartmentVo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BatchRepository extends MongoRepository<BatchVo, ObjectId> {

    List<BatchVo> findByCourseVo(CourseVo courseVo);

    List<BatchVo> findAllById(ObjectId id);
}
