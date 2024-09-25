package com.aksharspringboot.service;

import com.aksharspringboot.repository.WhiteBoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class WhiteBoardServiceImp implements WhiteBoardService{

    @Autowired
    private WhiteBoardRepository whiteBoardRepository;
}
