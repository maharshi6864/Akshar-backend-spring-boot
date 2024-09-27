package com.aksharspringboot.dto;

import com.aksharspringboot.model.PointVo;
import com.aksharspringboot.utils.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private PointVo pointVo;

    private long attendanceTimeStamp;

    private boolean attendanceStatus;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId studentId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId lectureId;

}
