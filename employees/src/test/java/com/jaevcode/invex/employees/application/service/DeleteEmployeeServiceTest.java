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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteEmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DeleteEmployeeService service;

    @Test
    void deleteEmployee_deletesWhenEmployeeExists() {
        when(employeeRepository.findById(10L)).thenReturn(Optional.of(Employee.builder().id(10L).build()));

        service.deleteEmployee(10L);

        verify(employeeRepository).findById(10L);
        verify(employeeRepository).deleteById(10L);
    }

    @Test
    void deleteEmployee_throwsWhenEmployeeNotFound() {
        when(employeeRepository.findById(77L)).thenReturn(Optional.empty());

        BusinessValidationException ex = assertThrows(
                BusinessValidationException.class,
                () -> service.deleteEmployee(77L)
        );

        assertEquals("Employee with id 77 not found", ex.getMessage());
        verify(employeeRepository).findById(77L);
        verify(employeeRepository, never()).deleteById(anyLong());
    }
}
