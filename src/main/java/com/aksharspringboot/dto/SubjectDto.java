package com.aksharspringboot.dto;

import com.aksharspringboot.model.ChapterVo;
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
public class SubjectDto {

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    private String subjectName;

    private List<ChapterVo> chapterVoList;

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId courseId;

    private String courseName;

    private String courseShortName;
}
