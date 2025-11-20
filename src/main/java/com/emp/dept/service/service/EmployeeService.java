package com.emp.dept.service.service;

import com.emp.dept.service.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(Long departmentId, EmployeeDTO employeeDTO);
    EmployeeDTO getEmployeeByID(Long departmentId, Long employeeId);
    List<EmployeeDTO> getAllEmployeeByDepartmentId(Long departmentId);
    EmployeeDTO updateEmployee(Long departmentId, Long employeeId, EmployeeDTO employeeDTO);
    void deleteEmployee(Long departmentId, Long employeeId);
}
