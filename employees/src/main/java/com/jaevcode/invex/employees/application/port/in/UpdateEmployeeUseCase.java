package com.jaevcode.invex.employees.application.port.in;

import com.jaevcode.invex.employees.domain.model.Employee;

public interface UpdateEmployeeUseCase {
    public Employee updateEmployee(long id, Employee employee);
}
