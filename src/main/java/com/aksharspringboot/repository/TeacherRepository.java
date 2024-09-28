package com.aksharspringboot.repository;

import com.aksharspringboot.model.CourseVo;
import com.aksharspringboot.model.DepartmentVo;
import com.aksharspringboot.model.TeacherVo;
import com.aksharspringboot.model.UserVo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeacherRepository extends MongoRepository<TeacherVo, ObjectId> {

    List<TeacherVo> findAllById(ObjectId id);

    List<TeacherVo> findByDepartmentVo(DepartmentVo departmentVo);

    List<TeacherVo> findByUserVo(UserVo userVo);
}
