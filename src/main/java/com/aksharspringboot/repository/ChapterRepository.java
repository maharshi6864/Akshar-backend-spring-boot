package com.aksharspringboot.repository;

import com.aksharspringboot.model.ChapterVo;
import com.aksharspringboot.model.SubjectVo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ChapterRepository extends MongoRepository<ChapterVo, ObjectId> {

    List<ChapterVo> findAllById(ObjectId id);

    List<ChapterVo> findBySubjectVo(SubjectVo subjectVo);

}