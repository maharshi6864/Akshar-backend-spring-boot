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

@Document(collection = "student_vo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentVo {

    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    @Indexed(unique = true)
    private String enrollmentNumber;

    private String firstName;

    private String lastName;

    @DBRef
    private UserVo userVo;

    @DBRef
    private CourseVo courseVo;

    @DBRef
    private SectionVo sectionVo;

}
