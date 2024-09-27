package com.aksharspringboot.repository;

import com.aksharspringboot.model.AttendanceVo;
import com.aksharspringboot.model.BatchVo;
import com.aksharspringboot.model.CourseVo;
import com.aksharspringboot.model.StudentVo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AttendanceRepository extends MongoRepository<AttendanceVo, ObjectId> {

    List<AttendanceVo> findAllById(ObjectId id);

    List<AttendanceVo> findAllByStudentVo(StudentVo studentVo);
}

