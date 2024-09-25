package com.aksharspringboot.model;

import com.aksharspringboot.utils.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "whiteboard_notes_vo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WhiteBoardVo {

    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private String notesName;

    private long time;

    @DBRef
    private LectureVo lectureVo;

    @DBRef
    private TeacherVo teacherVo;

    @DBRef
    private SectionVo sectionVo;
}
