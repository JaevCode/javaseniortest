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
class SearchEmployeeByNameServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private SearchEmployeeByNameService service;

    @Test
    void searchEmployee_returnsRepositoryResult() {
        List<Employee> employees = List.of(Employee.builder().id(1L).firstName("Jose").build());
        when(employeeRepository.findByName("Jose")).thenReturn(employees);

        List<Employee> result = service.searchEmployee("Jose");

        assertEquals(employees, result);
        verify(employeeRepository).findByName("Jose");
    }
}
