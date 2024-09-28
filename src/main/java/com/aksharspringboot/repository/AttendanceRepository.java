package com.aksharspringboot.repository;

import com.aksharspringboot.model.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends MongoRepository<AttendanceVo, ObjectId> {

    List<AttendanceVo> findAllById(ObjectId id);

    List<AttendanceVo> findAllByStudentVo(StudentVo studentVo);

    Optional<AttendanceVo> findByLectureVoAndStudentVo(LectureVo lectureVo, StudentVo studentVo);

}

