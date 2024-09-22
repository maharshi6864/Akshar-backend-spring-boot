package com.aksharspringboot.dto;

import com.aksharspringboot.model.TeacherVo;
import com.aksharspringboot.utils.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private String departmentId;

    private String departmentName;

    private String departmentShortName;

    private String teacherInfo;

    private int courses;

    private int teachers;
}
