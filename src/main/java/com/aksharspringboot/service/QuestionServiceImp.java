package com.aksharspringboot.service;

import com.aksharspringboot.dto.QuestionDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.SubjectDto;
import com.aksharspringboot.model.ChapterVo;
import com.aksharspringboot.model.QuestionVo;
import com.aksharspringboot.model.SubjectVo;
import com.aksharspringboot.repository.ChapterRepository;
import com.aksharspringboot.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class QuestionServiceImp implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public Response saveQuestion(QuestionDto questionDto)
    {
        QuestionVo questionVo = new QuestionVo(null,questionDto.getQuestion(),questionDto.getOptions()
                ,questionDto.getAnswer(),new ChapterVo(questionDto.getChapterId(),null,null));
        try {
            this.questionRepository.save(questionVo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to save question", null, false);
        }
        return new Response("Successfully saved question", questionVo, true);
    }

    @Override
    public Response getQuestionByChapter(ChapterVo chapterVo)
    {
        try {
            List<QuestionVo>  questionVoList= new ArrayList<>();
            List<QuestionDto>  questionDtoList= new ArrayList<>();

            questionVoList = this.questionRepository.findByChapterVo(chapterVo);

            for (QuestionVo questionVo : questionVoList) {
                chapterVo = this.chapterRepository.findAllById(questionVo.getChapterVo().getId()).get(0);
                QuestionDto questionDto= new QuestionDto(questionVo.getId(),questionVo.getQuestion(),questionVo.getOptions(),questionVo.getAnswer(),chapterVo.getId(),chapterVo.getName());
                questionDtoList.add(questionDto);
            }
            return new Response("Successfully searched question", Map.of("questionList",questionDtoList), true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to search questions.", null, false);
        }
    }
}
