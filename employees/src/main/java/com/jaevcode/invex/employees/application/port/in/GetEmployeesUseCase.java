package com.jaevcode.invex.employees.application.port.in;

import com.jaevcode.invex.employees.domain.model.Employee;

import java.util.List;

public interface GetEmployeesUseCase {
    public List<Employee> getAllEmployees();
}
