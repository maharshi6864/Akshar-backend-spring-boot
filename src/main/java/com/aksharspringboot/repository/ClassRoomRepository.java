package com.aksharspringboot.repository;

import com.aksharspringboot.model.ChapterVo;
import com.aksharspringboot.model.ClassRoomVo;
import com.aksharspringboot.model.SubjectVo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClassRoomRepository extends MongoRepository<ClassRoomVo, ObjectId> {

    List<ClassRoomVo> findAllById(ObjectId id);


}