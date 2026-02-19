package com.jaevcode.invex.employees.application.port.in;

import com.jaevcode.invex.employees.domain.model.Employee;

import java.util.List;

public interface SearchEmployeeByNameUseCase {
    public List<Employee> searchEmployee(String name);
}
