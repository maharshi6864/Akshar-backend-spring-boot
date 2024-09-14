package com.aksharspringboot.dto;

import com.aksharspringboot.model.DepartmentVo;
import com.aksharspringboot.model.UserVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

    private String id;

    private String teacherId;

    private String firstName;

    private String lastName;

    private int enabled;

    private String departmentId;

    private String departmentName;

    private String departmentShortName;
}
