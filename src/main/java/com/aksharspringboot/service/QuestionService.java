package com.aksharspringboot.service;

import com.aksharspringboot.dto.QuestionDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.model.ChapterVo;

public interface QuestionService {

    Response saveQuestion(QuestionDto questionDto);

    Response getQuestionByChapter(ChapterVo chapterVo);

}
