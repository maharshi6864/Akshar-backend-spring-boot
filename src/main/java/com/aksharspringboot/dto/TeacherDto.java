package com.aksharspringboot.dto;

import com.aksharspringboot.model.DepartmentVo;
import com.aksharspringboot.model.UserVo;
import com.aksharspringboot.utils.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private String teacherId;

    private String firstName;

    private String lastName;

    private String departmentId;

    private String departmentName;

    private String departmentShortName;
}
