package com.aksharspringboot.service;

import com.aksharspringboot.dto.AttendanceDto;
import com.aksharspringboot.dto.LectureDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.StudentDto;
import com.aksharspringboot.model.*;
import com.aksharspringboot.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class LectureServiceImp implements LectureService {

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private SubjectRepository subjectRepository;


    @Override
    public Response startLecture(LectureDto lectureDto) {
        CourseVo courseVo = new CourseVo(lectureDto.getCourseId(), null, null, null, null);
        BatchVo batchVo = new BatchVo(lectureDto.getBatchId(), null, null, null, null, null);
        SectionVo sectionVo = new SectionVo(lectureDto.getSectionId(), null, null);
        TeacherVo teacherVo = new TeacherVo(lectureDto.getTeacherId(), null, null, null, null, null);
        ClassRoomVo classRoomVo = new ClassRoomVo(lectureDto.getClassRoomId(), null, 0, 0, 0, 0, 0, 0, 0, 0);
        SubjectVo subjectVo=new SubjectVo(lectureDto.getSubjectId(),null,null);
        LectureVo lectureVo = new LectureVo(null, lectureDto.getLectureTime(), System.currentTimeMillis(),
                0, true, courseVo, batchVo, sectionVo, teacherVo, classRoomVo,subjectVo);
        try {
            LectureVo savedLectureVo = this.lectureRepository.save(lectureVo);
            lectureDto.setId(savedLectureVo.getId());
            return new Response("New Lecture Created", lectureDto, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to start lecture.", lectureDto, false);
        }
    }

    @Override
    public Response endLecture(LectureDto lectureDto) {
        try {
            LectureVo lectureVo = this.lectureRepository.findAllById(lectureDto.getId()).get(0);
            lectureVo.setLectureStatus(false);
            lectureVo.setLectureEndTimeStamp(System.currentTimeMillis());
            LectureVo savedLectureVo = this.lectureRepository.save(lectureVo);
            lectureDto.setId(savedLectureVo.getId());
            return new Response("Lecture Ended SuccessFully", lectureDto, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to end the lecture.", lectureDto, false);
        }
    }

    @Override
    public Response getLecturesByTeacherId(TeacherVo teacherVo) {
        try {
            List<LectureVo> lectureVoList = this.lectureRepository.findByTeacherVo(teacherVo);
            List<LectureDto> lectureDtoList = new ArrayList<>();

            for (LectureVo lectureVo : lectureVoList) {
                CourseVo courseVo = this.courseRepository.findAllById(lectureVo.getCourseVo().getId()).get(0);
                BatchVo batchVo = this.batchRepository.findAllById(lectureVo.getBatchVo().getId()).get(0);
                SectionVo sectionVo = this.sectionRepository.findAllById(lectureVo.getSectionVo().getId()).get(0);
                ClassRoomVo classRoomVo = this.classRoomRepository.findAllById(lectureVo.getClassRoomVo().getId()).get(0);
                TeacherVo teacherVo1=this.teacherRepository.findAllById(lectureVo.getTeacherVo().getId()).get(0);
                SubjectVo subjectVo=this.subjectRepository.findAllById(lectureVo.getSubjectVo().getId()).get(0);
                LectureDto lectureDto = new LectureDto(lectureVo.getId(), lectureVo.getLectureActualTimings(),
                        batchVo.getBatchName(), sectionVo.getSectionName(), courseVo.getCourseName(),teacherVo1.getFirstName()+teacherVo1.getLastName(),lectureVo.isLectureStatus(),subjectVo.getSubjectName(), classRoomVo.getClassRoomNumber(),
                        lectureVo.getLectureStartTimeStamp(), lectureVo.getLectureEndTimeStamp(),false, null,
                        null, null, null, null,null);
                lectureDtoList.add(lectureDto);
            }
            return new Response("Successfully searched lecture For Teacher", Map.of("lectureDto", lectureDtoList), true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to search lectures for teacher.", null, false);
        }
    }

    @Override
    public Response getLecturesByStudentId(StudentVo studentVo) {
        try {
            StudentVo studentVo1 = this.studentRepository.findAllById(studentVo.getId()).get(0);
            SectionVo sectionVo = studentVo1.getSectionVo();
            List<LectureVo> lectureVoList = this.lectureRepository.findBySectionVo(sectionVo);
            List<LectureDto> lectureDtoList = new ArrayList<>();

            for (LectureVo lectureVo : lectureVoList) {
                CourseVo courseVo = this.courseRepository.findAllById(lectureVo.getCourseVo().getId()).get(0);
                BatchVo batchVo = this.batchRepository.findAllById(lectureVo.getBatchVo().getId()).get(0);
//                SectionVo sectionVo = this.sectionRepository.findAllById(lectureVo.getSectionVo().getId()).get(0);
                ClassRoomVo classRoomVo = this.classRoomRepository.findAllById(lectureVo.getClassRoomVo().getId()).get(0);
                TeacherVo teacherVo1=this.teacherRepository.findAllById(lectureVo.getTeacherVo().getId()).get(0);
                SubjectVo subjectVo=this.subjectRepository.findAllById(lectureVo.getSubjectVo().getId()).get(0);
                Optional<AttendanceVo> existingAttendance = this.attendanceRepository.findByLectureVoAndStudentVo(lectureVo, studentVo);

                AttendanceVo attendanceVo;

                if (existingAttendance.isPresent()) {
                    // If attendance exists, use the existing attendance
                    attendanceVo = existingAttendance.get();
                } else {
                    // If attendance doesn't exist, create a new one
                    attendanceVo = new AttendanceVo(null, 0, false, lectureVo.getClassRoomVo(), studentVo, lectureVo.getTeacherVo(),lectureVo);
                    attendanceVo = this.attendanceRepository.save(attendanceVo);
                }
                LectureDto lectureDto = new LectureDto(lectureVo.getId(), lectureVo.getLectureActualTimings(),
                        batchVo.getBatchName(), sectionVo.getSectionName(), courseVo.getCourseName(),teacherVo1.getFirstName()+teacherVo1.getLastName(),lectureVo.isLectureStatus(),subjectVo.getSubjectName(), classRoomVo.getClassRoomNumber(),
                        lectureVo.getLectureStartTimeStamp(), lectureVo.getLectureEndTimeStamp(),attendanceVo.isAttendanceStatus(), null,
                        null, null, null, null,null);
                lectureDtoList.add(lectureDto);
            }
            return new Response("Successfully searched lecture For Student", Map.of("lectureDto", lectureDtoList), true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Failed to search lectures for Student.", null, false);
        }
    }

    @Override
    public Response getStudentForCurrentLecture(LectureVo lectureVo) {
        try {
            // Fetch lecture by ID
            LectureVo lectureVo1 = this.lectureRepository.findAllById(lectureVo.getId()).get(0);

            // Get list of students for the section of this lecture
            List<StudentVo> studentVoList = this.studentRepository.findBySectionVo(lectureVo1.getSectionVo());
            List<StudentDto> studentDtoList = new ArrayList<>();

            for (StudentVo studentVo : studentVoList) {
                // Check if attendance already exists for this student in the current lecture
                Optional<AttendanceVo> existingAttendance = this.attendanceRepository.findByLectureVoAndStudentVo(lectureVo1, studentVo);

                AttendanceVo attendanceVo;

                if (existingAttendance.isPresent()) {
                    // If attendance exists, use the existing attendance
                    attendanceVo = existingAttendance.get();
                } else {
                    // If attendance doesn't exist, create a new one
                    attendanceVo = new AttendanceVo(null, 0, false, lectureVo.getClassRoomVo(), studentVo, lectureVo.getTeacherVo(),lectureVo);
                    attendanceVo = this.attendanceRepository.save(attendanceVo);
                }

                // Map student to DTO
                StudentDto studentDto = new StudentDto(
                        studentVo.getId(),
                        studentVo.getEnrollmentNumber(),
                        studentVo.getFirstName(),
                        studentVo.getLastName(),
                        studentVo.getEmailAddress(),
                        studentVo.getFilePath(),
                        attendanceVo.isAttendanceStatus(),
                        attendanceVo.getId(),
                        studentVo.getCourseVo().getId(),
                        studentVo.getCourseVo().getCourseName(),
                        studentVo.getCourseVo().getCourseShortName(),
                        null
                );
                studentDtoList.add(studentDto);
            }

            return new Response("Student searched successfully.", Map.of("studentList", studentDtoList), true);
        } catch (Exception e) {
            return new Response("Failed to search Student.", null, false);
        }
    }


    @Override
    public Response markAttendance(AttendanceDto attendanceDto) {
        try {
            // Fetch the existing attendance record using the ID from AttendanceDto
            Optional<AttendanceVo> attendanceOptional = this.attendanceRepository.findById(attendanceDto.getId());

            if (attendanceOptional.isPresent()) {
                // If attendance exists, update its status
                AttendanceVo attendanceVo = attendanceOptional.get();

                // Set the new attendance status from the DTO
                attendanceVo.setAttendanceStatus(attendanceDto.isAttendanceStatus()); // Assuming attendanceStatus is a boolean

                // Save the updated attendance record
                attendanceRepository.save(attendanceVo);

                return new Response("Attendance marked successfully.", null, true);
            } else {
                // If attendance record is not found
                return new Response("Attendance record not found.", null, false);
            }
        } catch (Exception e) {
            // Handle any exceptions and return a failure response
            return new Response("Failed to mark attendance.", null, false);
        }
    }

}
