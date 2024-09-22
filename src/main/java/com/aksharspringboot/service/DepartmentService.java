package com.aksharspringboot.service;

import com.aksharspringboot.dto.DepartmentDto;
import com.aksharspringboot.dto.Response;

public interface DepartmentService {

    Response addDepartment(DepartmentDto departmentDto);

    Response getAllDepartment();

    Response updateDepartment(DepartmentDto departmentDto);

    Response checkForDepartmentIdAvail(DepartmentDto departmentDto);

    Response deleteDepartment(DepartmentDto departmentDto);

}
