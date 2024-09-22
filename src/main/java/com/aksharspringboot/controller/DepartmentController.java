package com.aksharspringboot.controller;

import com.aksharspringboot.dto.DepartmentDto;
import com.aksharspringboot.dto.Response;
import com.aksharspringboot.service.DepartmentService;
import com.aksharspringboot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("admin/department/addDepartment")
    public ResponseEntity<Response> addDepartment(@RequestBody DepartmentDto departmentDto) {
        Response response = this.departmentService.addDepartment(departmentDto);
        return new ResponseEntity<>( response, HttpStatus.OK);
    }

    @GetMapping("admin/department/getAllDepartment")
    public ResponseEntity<Response> getAllDepartment() {
        Response response = this.departmentService.getAllDepartment();
        return new ResponseEntity<>( response, HttpStatus.OK);
    }

    @PostMapping("admin/department/updateDepartment")
    public ResponseEntity<Response> updateDepartment(@RequestBody DepartmentDto departmentDto) {
        Response response = this.departmentService.updateDepartment(departmentDto);
        return new ResponseEntity<>( response, HttpStatus.OK);
    }

    @PostMapping("admin/department/deleteDepartment")
    public ResponseEntity<Response> deleteDepartment(@RequestBody DepartmentDto departmentDto) {
        Response response = this.departmentService.deleteDepartment(departmentDto);
        return new ResponseEntity<>( response, HttpStatus.OK);
    }

    @PostMapping("admin/department/checkForDepartmentIdAvail")
    public ResponseEntity<Response> checkForDepartmentIdAvail(@RequestBody DepartmentDto departmentDto) {
        Response response = this.departmentService.checkForDepartmentIdAvail(departmentDto);
        return new ResponseEntity<>( response, HttpStatus.OK);
    }

}
