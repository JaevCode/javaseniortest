package com.jaevcode.invex.employees.application.port.in;

import com.jaevcode.invex.employees.domain.model.Employee;

import java.util.List;

public interface CreateEmployeesUseCase {
    public List<Employee> createEmployees(List<Employee> employee);
}
