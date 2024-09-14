package com.aksharspringboot.repository;

import com.aksharspringboot.model.TeacherVo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<TeacherVo, ObjectId> {
}
