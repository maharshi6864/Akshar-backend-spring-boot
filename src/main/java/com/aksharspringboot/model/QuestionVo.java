package com.aksharspringboot.model;

import com.aksharspringboot.utils.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "question_vo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionVo {

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private String question;

    private List<String> options;

    private String answer;

    @DBRef
    private ChapterVo chapterVo;
}
