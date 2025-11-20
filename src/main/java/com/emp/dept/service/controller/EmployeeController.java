package com.emp.dept.service.controller;

import com.emp.dept.service.dto.EmployeeDTO;
import com.emp.dept.service.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/{departmentId}/employees")
    public ResponseEntity<EmployeeDTO> createEmployee(@PathVariable("departmentId") Long departmentId,
                                                      @RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO savedEmployeeDTO = employeeService.createEmployee(departmentId, employeeDTO);

        return new ResponseEntity<>(savedEmployeeDTO,  HttpStatus.CREATED);
    }

    @GetMapping("/{departmentId}/employees/{id}")
    public ResponseEntity<EmployeeDTO> createEmployee(@PathVariable("departmentId") Long departmentId,
                                                      @PathVariable("id") Long employeeId){
        EmployeeDTO foundEmployeeDTO = employeeService.getEmployeeByID(departmentId, employeeId);
        return new ResponseEntity<>(foundEmployeeDTO, HttpStatus.FOUND);
    }

    @GetMapping("/{departmentId}/employees")
    public ResponseEntity<List<EmployeeDTO>> createEmployee(@PathVariable("departmentId") Long departmentId){
        List<EmployeeDTO> foundEmployeeDTOList = employeeService.getAllEmployeeByDepartmentId(departmentId);
        return new ResponseEntity<>(foundEmployeeDTOList, HttpStatus.OK);
    }

    @PutMapping("/{departmentId}/employees/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("departmentId") Long departmentId,
                                                      @PathVariable("id") Long employeeId,
                                                      @RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO savedEmployeeDTO = employeeService.updateEmployee(departmentId, employeeId, employeeDTO);

        return new ResponseEntity<>(savedEmployeeDTO,  HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("departmentId") Long departmentId,
                                                      @PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(departmentId, employeeId);

        return new ResponseEntity<>("Employee deleted successfully!!",  HttpStatus.NO_CONTENT);
    }
}
