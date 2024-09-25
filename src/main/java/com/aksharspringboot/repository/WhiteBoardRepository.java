package com.aksharspringboot.repository;

import com.aksharspringboot.model.WhiteBoardVo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WhiteBoardRepository extends MongoRepository<WhiteBoardVo, ObjectId> {

    List<WhiteBoardVo> findAllById(ObjectId id);

}

