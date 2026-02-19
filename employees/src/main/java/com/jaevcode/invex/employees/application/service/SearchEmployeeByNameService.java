package com.jaevcode.invex.employees.application.service;

import com.jaevcode.invex.employees.application.port.in.SearchEmployeeByNameUseCase;
import com.jaevcode.invex.employees.application.port.out.EmployeeRepository;
import com.jaevcode.invex.employees.domain.model.Employee;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SearchEmployeeByNameService implements SearchEmployeeByNameUseCase {
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> searchEmployee(String name) {
        return employeeRepository.findByName(name);
    }
}
