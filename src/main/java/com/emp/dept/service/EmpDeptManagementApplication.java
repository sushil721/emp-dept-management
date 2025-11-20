package com.emp.dept.service;

import com.emp.dept.service.dto.EmployeeDTO;
import com.emp.dept.service.entity.Employee;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmpDeptManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpDeptManagementApplication.class, args);
	}

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper().registerModule(new RecordModule());

    }

}
