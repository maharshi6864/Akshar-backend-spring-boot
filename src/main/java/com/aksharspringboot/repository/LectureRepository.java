package com.aksharspringboot.repository;

import com.aksharspringboot.model.BatchVo;
import com.aksharspringboot.model.LectureVo;
import com.aksharspringboot.model.SectionVo;
import com.aksharspringboot.model.TeacherVo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LectureRepository extends MongoRepository<LectureVo, ObjectId> {

    List<LectureVo> findBySectionVo(SectionVo sectionVo);

    List<LectureVo> findAllById(ObjectId id);

    List<LectureVo> findByTeacherVo(TeacherVo teacherVo);
}
