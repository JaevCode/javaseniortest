package com.jaevcode.invex.employees.application.service;

import com.jaevcode.invex.employees.application.port.in.DeleteEmployeeUseCase;
import com.jaevcode.invex.employees.application.port.out.EmployeeRepository;
import com.jaevcode.invex.employees.common.exception.BusinessValidationException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteEmployeeService implements DeleteEmployeeUseCase {
    private EmployeeRepository employeeRepository;

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow(() -> new BusinessValidationException("Employee with id " + id + " not found"));
        employeeRepository.deleteById(id);
    }
}
