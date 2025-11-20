package com.emp.dept.service.controller;

import com.emp.dept.service.dto.DepartmentDTO;
import com.emp.dept.service.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO){
        DepartmentDTO SavedDepartmentDTO = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(SavedDepartmentDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById( @PathVariable("id") Long deptId){
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(deptId);
        return new ResponseEntity<>(departmentDTO, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getDepartmentById(){
        List<DepartmentDTO> departments = departmentService.getAllDepartment();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment( @PathVariable("id") Long deptId,
                                                           @RequestBody DepartmentDTO departmentDTO){
        DepartmentDTO updatedDepartmentDTO = departmentService.updateDepartment(deptId, departmentDTO);
        return new ResponseEntity<>(updatedDepartmentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long deptId){
        departmentService.deleteDepartment(deptId);
        return new ResponseEntity<>("Department deletion successful", HttpStatus.NO_CONTENT);
    }
}
