package com.jaevcode.invex.employees.application.service;

import com.jaevcode.invex.employees.application.port.in.GetEmployeeByIdUseCase;
import com.jaevcode.invex.employees.application.port.out.EmployeeRepository;
import com.jaevcode.invex.employees.common.exception.BusinessValidationException;
import com.jaevcode.invex.employees.domain.model.Employee;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetEmployeeByIdService implements GetEmployeeByIdUseCase {
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new BusinessValidationException("Employee with id " + id + " not found"));
    }
}
