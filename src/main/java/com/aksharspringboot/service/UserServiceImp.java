package com.aksharspringboot.service;


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

}
