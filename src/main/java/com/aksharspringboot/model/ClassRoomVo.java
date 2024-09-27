package com.aksharspringboot.model;

import com.aksharspringboot.utils.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "department_vo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoomVo {

    @Id
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
