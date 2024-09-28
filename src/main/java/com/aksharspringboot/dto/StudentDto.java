package com.aksharspringboot.dto;

import com.aksharspringboot.utils.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {


    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private String enrollmentNumber;

    private String firstName;

    private String lastName;

    private String studentEmailAddress;

    private String filePath;

    private boolean attendanceStatus;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId attendanceId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId courseId;

    private String courseName;

    private String courseShortName;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId sectionId;
}
