package com.aksharspringboot.service;

import com.aksharspringboot.repository.ClassRoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClassRoomServiceImp implements ClassRoomService{

    @Autowired
    private ClassRoomRepository classRoomRepository;

        
}
