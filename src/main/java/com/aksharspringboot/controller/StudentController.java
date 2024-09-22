package com.aksharspringboot.controller;

import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.StudentDto;
import com.aksharspringboot.dto.TeacherDto;
import com.aksharspringboot.service.StudentService;
import com.aksharspringboot.service.TeacherService;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ChatModel chatModel;

    @PostMapping("admin/student/addStudent")
    public ResponseEntity<Response> addStudent(@RequestBody StudentDto studentDto)
    {
        Response response=this.studentService.addStudent(studentDto);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @GetMapping("admin/student/getAllStudent")
    public ResponseEntity<Response> getAllStudent()
    {
        Response response=this.studentService.getAllStudent();
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @PostMapping("admin/student/updateStudent")
    public ResponseEntity<Response> updateStudent(@RequestBody StudentDto studentDto)
    {
        Response response=this.studentService.updateStudent(studentDto);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @PostMapping("admin/student/askQuestion")
    public ResponseEntity<Response> askQuestion(@RequestBody HashMap<String,String> map)
    {
        String prompt=map.get("prompt");
        System.out.println(prompt);
        ChatResponse chatResponse = chatModel.call(
                new Prompt(
                        map.get("prompt"),
                        OpenAiChatOptions.builder()
                                .withModel("gpt-3.5-turbo")
                                .withTemperature(0.4f)
                                .build()
                ));

        Response response=new Response("chat response",chatResponse,true);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

}
