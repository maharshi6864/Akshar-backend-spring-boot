package com.aksharspringboot.repository;

import com.aksharspringboot.model.DepartmentVo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DepartmentRepository extends MongoRepository<DepartmentVo, ObjectId> {

    List<DepartmentVo> findByDepartmentId(String departmentId);

    List<DepartmentVo> findAllById(ObjectId id);
}
