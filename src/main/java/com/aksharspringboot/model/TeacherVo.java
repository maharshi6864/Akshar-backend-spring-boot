package com.aksharspringboot.model;

import com.aksharspringboot.utils.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teacher_vo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherVo {

    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    @NonNull
    @Indexed(unique = true)
    private String teacherId;

    private String firstName;

    private String lastName;

    @DBRef
    private UserVo userVo;

    @DBRef
    private DepartmentVo departmentVo;

}
