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
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "department_vo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentVo {

    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String departmentId;

    private String departmentName;

    private String departmentShortName;

    private String teacherInfo;

}
