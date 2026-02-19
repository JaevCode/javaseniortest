package com.jaevcode.invex.employees.application.port.in;

import com.jaevcode.invex.employees.domain.model.Employee;

public interface GetEmployeeByIdUseCase {
    public Employee getEmployeeById(long id);
}
