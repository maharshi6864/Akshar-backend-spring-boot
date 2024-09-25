package com.aksharspringboot.repository;

import com.aksharspringboot.model.BatchVo;
import com.aksharspringboot.model.ChapterVo;
import com.aksharspringboot.model.QuestionVo;
import com.aksharspringboot.model.SectionVo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface QuestionRepository extends MongoRepository<QuestionVo, ObjectId> {

    List<QuestionVo> findByChapterVo(ChapterVo chapterVo);

}
