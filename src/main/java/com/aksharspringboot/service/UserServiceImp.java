package com.aksharspringboot.service;


import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.UserDto;
import com.aksharspringboot.model.UserVo;
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
            UserDto userDto=new UserDto(userVo.getUsername(),userVo.getRole());
            return new Response("User Details Found",userDto,true);
        }else if (userVo.getRole().equals("TEACHER"))
        {
            UserDto userDto=new UserDto(userVo.getUsername(),userVo.getRole());
            return new Response("User Details Found",userDto,true);
        }else if(userVo.getRole().equals("STUDENT"))
        {
            UserDto userDto=new UserDto(userVo.getUsername(),userVo.getRole());
            return new Response("User Details Found",userDto,true);
        }
        return new Response("Failed To found User Details",null,true);
    }

}
