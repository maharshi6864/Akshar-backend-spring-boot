package com.aksharspringboot.repository;

import com.aksharspringboot.model.StudentVo;
import com.aksharspringboot.model.UserVo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository extends MongoRepository<StudentVo, ObjectId> {

    List<StudentVo> findAllById(ObjectId id);

    List<StudentVo> findByUserVo(UserVo userVo);
}
