package com.jaevcode.invex.employees.application.service;

import com.jaevcode.invex.employees.application.port.out.EmployeeRepository;
import com.jaevcode.invex.employees.common.exception.BusinessValidationException;
import com.jaevcode.invex.employees.domain.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetEmployeeByIdServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private GetEmployeeByIdService service;

    @Test
    void getEmployeeById_returnsEmployeeWhenFound() {
        Employee employee = Employee.builder().id(1L).firstName("Jose").build();
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = service.getEmployeeById(1L);

        assertEquals(employee, result);
        verify(employeeRepository).findById(1L);
    }

    @Test
    void getEmployeeById_throwsWhenEmployeeNotFound() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        BusinessValidationException ex = assertThrows(
                BusinessValidationException.class,
                () -> service.getEmployeeById(99L)
        );

        assertEquals("Employee with id 99 not found", ex.getMessage());
        verify(employeeRepository).findById(99L);
    }
}
