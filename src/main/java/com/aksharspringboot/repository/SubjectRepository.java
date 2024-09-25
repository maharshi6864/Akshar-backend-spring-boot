package com.aksharspringboot.repository;

import com.aksharspringboot.model.CourseVo;
import com.aksharspringboot.model.SectionVo;
import com.aksharspringboot.model.SubjectVo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubjectRepository extends MongoRepository<SubjectVo, ObjectId> {

    List<SubjectVo> findAllById(ObjectId id);

    List<SubjectVo> findSubjectVoByCourseVo(CourseVo courseVo);
}
