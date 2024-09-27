package com.aksharspringboot.model;

import com.aksharspringboot.utils.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "attendance_vo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceVo {

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private long attendanceTimeStamp;

    private boolean attendanceStatus;

    @DBRef
    private ClassRoomVo classRoomVo;

    @DBRef
    private StudentVo studentVo;

    @DBRef
    private TeacherVo teacherVo;
}
