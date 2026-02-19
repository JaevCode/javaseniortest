package com.jaevcode.invex.employees.application.service;

import com.jaevcode.invex.employees.application.port.in.GetEmployeesUseCase;
import com.jaevcode.invex.employees.application.port.out.EmployeeRepository;
import com.jaevcode.invex.employees.domain.model.Employee;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetEmployeesService implements GetEmployeesUseCase {

    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAll();
    }
}
