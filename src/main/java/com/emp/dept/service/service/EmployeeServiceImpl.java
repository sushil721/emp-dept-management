package com.emp.dept.service.service;

import com.emp.dept.service.dto.EmployeeDTO;
import com.emp.dept.service.entity.Department;
import com.emp.dept.service.entity.Employee;
import com.emp.dept.service.exception.BadRequestException;
import com.emp.dept.service.exception.ResourceNotFoundException;
import com.emp.dept.service.repository.DepartmentRepository;
import com.emp.dept.service.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDTO createEmployee(Long departmentId, EmployeeDTO employeeDTO) {
        //First, We'll retrieve the department from database using departmentID.
        //if, Department not exist throw the ResourceNotFoundException.
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist followed by ID: "+departmentId));

        // Second, We'll convert EmployeeDTO to Employee entity object.
        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        //Third, We'll associate Employee entity with the retrieved Department entity.
        employee.setDepartment(department);

        // Fourth, We'll save Employee entity into the database.
        Employee savedEmployee = employeeRepository.save(employee);

        //Fifth, We'll convert the saved Employee entity into
        // an EmployeeDTO object before return it.
        EmployeeDTO savedEmployeeDTO = modelMapper.map(savedEmployee, EmployeeDTO.class);
        savedEmployeeDTO.setDepartmentId(departmentId);

        //Finally, return savedEmployeeDTO with departmentId.
        return savedEmployeeDTO;
    }

    @Override
    public EmployeeDTO getEmployeeByID(Long departmentId, Long employeeId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist followed by ID: "+departmentId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist followed by ID: "+employeeId));

        if(!employee.getDepartment().getId().equals(department.getId())){
            throw new BadRequestException("This employee doesn't belong to Department with ID: "+ departmentId);
        }

        EmployeeDTO foundEmployeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        foundEmployeeDTO.setDepartmentId(employee.getDepartment().getId());

        return foundEmployeeDTO;
    }

    @Override
    public List<EmployeeDTO> getAllEmployeeByDepartmentId(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist followed by ID: "+departmentId));

        List<Employee> empployeeList = employeeRepository.findByDepartmentId(departmentId);
        return empployeeList
                .stream()
                .map((emp) -> modelMapper.map(emp, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long departmentId, Long employeeId, EmployeeDTO employeeDTO) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist followed by ID: "+departmentId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist followed by ID: "+employeeId));

        if(!employee.getDepartment().getId().equals(department.getId())){
            throw new BadRequestException("This employee doesn't belong to Department with ID: "+ departmentId);
        }
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDTO savedEmployeeDTO = modelMapper.map(savedEmployee, EmployeeDTO.class);
        savedEmployeeDTO.setDepartmentId(employee.getDepartment().getId());

        return savedEmployeeDTO;
    }

    @Override
    public void deleteEmployee(Long departmentId, Long employeeId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist followed by ID: "+departmentId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist followed by ID: "+employeeId));

        if(!employee.getDepartment().getId().equals(department.getId())){
            throw new BadRequestException("This employee doesn't belong to Department with ID: "+ departmentId);
        }
        employeeRepository.delete(employee);
    }

}
