package com.aksharspringboot.controller;

import com.aksharspringboot.dto.AttendanceDto;
import com.aksharspringboot.dto.LectureDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.model.AttendanceVo;
import com.aksharspringboot.model.LectureVo;
import com.aksharspringboot.model.StudentVo;
import com.aksharspringboot.model.TeacherVo;
import com.aksharspringboot.service.LectureService;
import com.aksharspringboot.service.WhiteBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LectureController {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private WhiteBoardService whiteBoardService;

    @PostMapping("teacher/lecture/startLecture")
    public ResponseEntity<Response> createLecture(@RequestBody LectureDto lectureDto)
    {
        return new ResponseEntity<>(this.lectureService.startLecture(lectureDto), HttpStatus.OK);
    }

    @PostMapping("teacher/lecture/endLecture")
    public ResponseEntity<Response> endLecture(@RequestBody LectureDto lectureDto)
    {
        return new ResponseEntity<>(this.lectureService.endLecture(lectureDto), HttpStatus.OK);
    }

    @PostMapping("teacher/lecture/getLecturesByTeacherId")
    public ResponseEntity<Response> getLecturesByTeacherId(@RequestBody TeacherVo teacherVo)
    {
        Response response=this.lectureService.getLecturesByTeacherId(teacherVo);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("teacher/lecture/getStudentForCurrentLecture")
    public ResponseEntity<Response> getStudentForCurrentLecture(@RequestBody LectureVo lectureVo)
    {
        Response response=this.lectureService.getStudentForCurrentLecture(lectureVo);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("teacher/lecture/markAttendance")
    public ResponseEntity<Response> markAttendance(@RequestBody AttendanceDto attendanceDto)
    {
        Response response=this.lectureService.markAttendance(attendanceDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("student/lecture/downloadNotes")
    public ResponseEntity<byte[]> downloadNotes(@RequestBody LectureDto lectureDto) {
        // Call the service to get the zip file as a byte array
        Response response = this.whiteBoardService.downloadNotes(lectureDto);

        // Retrieve the byte array for the zip file from the response
        try{
            Map<?,?> map = (Map<?, ?>) response.getBody();
            byte[] zipFile= (byte[]) map.get("notesZipFile");



        // Prepare headers for returning the file as a downloadable attachment
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // MIME type for zip file
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("notes.zip").build());
        headers.setContentLength(zipFile.length); // Set content length for proper file download
            return new ResponseEntity<>(zipFile, headers, HttpStatus.OK);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(null, null, HttpStatus.OK);
        }

        // Return the zip file as a ResponseEntity with the correct headers

    }



    @PostMapping("student/lecture/getLecturesByStudentId")
    public ResponseEntity<Response> getLecturesByStudentId(@RequestBody StudentVo studentVo)
    {
        Response response=this.lectureService.getLecturesByStudentId(studentVo);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}