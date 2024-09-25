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
public class QuestionDto {

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private String question;

    private List<String> options;

    private String answer;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId chapterId;

    private String chapterName;

}
