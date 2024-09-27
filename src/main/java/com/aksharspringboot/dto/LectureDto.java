package com.aksharspringboot.dto;

import com.aksharspringboot.utils.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureDto {

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private String lectureTime;

    private String batchName;

    private String sectionName;

    private String courseName;

    private String classRoomNumber;

    private long lectureStartTime;

    private long lectureEndTime;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId courseId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId batchId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId sectionId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId teacherId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId classRoomId;


}
