package com.aksharspringboot.repository;

import com.aksharspringboot.model.StudentVo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<StudentVo, ObjectId> {
}
