package com.jaevcode.invex.employees.application.service;

import com.jaevcode.invex.employees.application.port.out.EmployeeRepository;
import com.jaevcode.invex.employees.domain.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetEmployeesServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private GetEmployeesService service;

    @Test
    void getAllEmployees_returnsRepositoryResult() {
        List<Employee> employees = List.of(
                Employee.builder().id(1L).firstName("Jose").build(),
                Employee.builder().id(2L).firstName("Ana").build()
        );
        when(employeeRepository.getAll()).thenReturn(employees);

        List<Employee> result = service.getAllEmployees();

        assertEquals(employees, result);
        verify(employeeRepository).getAll();
    }
}
