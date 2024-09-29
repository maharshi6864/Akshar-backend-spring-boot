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
public class WhiteBoardDto {

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId lectureId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId teacherId;

    private String notesName; // To store the name/title of the SVG

    private long time; // To store the timestamp or time of creation

    private String svgContent; // To store the SVG as a string
}
