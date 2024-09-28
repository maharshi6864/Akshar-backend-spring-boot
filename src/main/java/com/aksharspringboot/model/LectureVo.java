package com.aksharspringboot.model;

import com.aksharspringboot.utils.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "lecture_vo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureVo {

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private String lectureActualTimings;

    private long lectureStartTimeStamp;

    private long lectureEndTimeStamp;

    private boolean lectureStatus;

    @DBRef
    private CourseVo courseVo;

    @DBRef
    private BatchVo batchVo;

    @DBRef
    private SectionVo sectionVo;

    @DBRef
    private TeacherVo teacherVo;

    @DBRef
    private ClassRoomVo classRoomVo;

    @DBRef
    private SubjectVo subjectVo;
}
