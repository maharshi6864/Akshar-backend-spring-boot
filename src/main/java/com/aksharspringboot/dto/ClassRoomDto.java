package com.aksharspringboot.dto;

import com.aksharspringboot.utils.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoomDto {
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private String classRoomNumber;

    private double topRightLongitude;

    private double topRightLatitude;

    private double topLeftLongitude;

    private double topLeftLatitude;

    private double bottomRightLongitude;

    private double bottomRightLatitude;

    private double bottomLeftLongitude;

    private double bottomLeftLatitude;
}
