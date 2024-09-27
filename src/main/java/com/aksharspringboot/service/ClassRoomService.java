package com.aksharspringboot.service;

import com.aksharspringboot.dto.ClassRoomDto;
import com.aksharspringboot.dto.Response;

public interface ClassRoomService {

    Response addClassRoom(ClassRoomDto classRoomDto);

    Response addClassRoomMapping(ClassRoomDto classRoomDto);

    Response getClassRoom();

    Response getClassRoomById(ClassRoomDto classRoomDto);
}
