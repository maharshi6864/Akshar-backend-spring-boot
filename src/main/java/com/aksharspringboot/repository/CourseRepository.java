package com.aksharspringboot.repository;

import com.aksharspringboot.model.CourseVo;
import com.aksharspringboot.model.DepartmentVo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<CourseVo, ObjectId> {

    List<CourseVo> findByCourseId(String courseId);

    List<CourseVo> findAllById(ObjectId id);
}
