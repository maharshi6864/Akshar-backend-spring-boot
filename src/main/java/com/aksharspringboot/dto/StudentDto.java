package com.aksharspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private String id;

    private String enrollmentNumber;

    private String firstName;

    private String lastName;

    private String courseId;

    private String courseName;

    private String courseShortName;
}
