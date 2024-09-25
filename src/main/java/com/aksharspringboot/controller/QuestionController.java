package com.aksharspringboot.controller;

import com.aksharspringboot.dto.QuestionDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.model.ChapterVo;
import com.aksharspringboot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("teacher/question/saveQuestion")
    public ResponseEntity<Response> saveQuestion(@RequestBody QuestionDto questionDto)
    {
        Response response = this.questionService.saveQuestion(questionDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("teacher/question/getQuestionByChapterId")
    public ResponseEntity<Response> saveQuestion(@RequestBody ChapterVo chapterVo)
    {
        Response response = this.questionService.getQuestionByChapter(chapterVo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
