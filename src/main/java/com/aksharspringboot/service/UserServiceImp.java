package com.aksharspringboot.service;


import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.UserDto;
import com.aksharspringboot.model.StudentVo;
import com.aksharspringboot.model.TeacherVo;
import com.aksharspringboot.model.UserVo;
import com.aksharspringboot.repository.StudentRepository;
import com.aksharspringboot.repository.TeacherRepository;
import com.aksharspringboot.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void insertUser(UserVo user) {
        userRepository.save(user);
    }

    @Override
    public UserVo findByUserName(String username) {
        return this.userRepository.findByUsername(username).get(0);
    }

    @Override
    public UserVo getCurrentUser() {
        return this.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public Response getUserDetails() {
        UserVo userVo=this.getCurrentUser();
        if (userVo.getRole().equals("ADMIN"))
        {

            UserDto userDto=new UserDto(null,userVo.getUsername(),userVo.getRole());
            return new Response("User Details Found",userDto,true);
        }else if (userVo.getRole().equals("TEACHER"))
        {
            TeacherVo teacherVo=this.teacherRepository.findByUserVo(userVo).get(0);
            UserDto userDto=new UserDto(teacherVo.getId(),userVo.getUsername(),userVo.getRole());
            return new Response("User Details Found",userDto,true);
        }else if(userVo.getRole().equals("STUDENT"))
        {
            StudentVo studentVo=this.studentRepository.findByUserVo(userVo).get(0);
            UserDto userDto=new UserDto(studentVo.getId(),userVo.getUsername(),userVo.getRole());
            return new Response("User Details Found",userDto,true);
        }
        return new Response("Failed To found User Details",null,true);
    }

}
