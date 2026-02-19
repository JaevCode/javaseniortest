package com.jaevcode.invex.employees.application.service;

import com.jaevcode.invex.employees.application.port.out.EmployeeRepository;
import com.jaevcode.invex.employees.common.exception.BusinessValidationException;
import com.jaevcode.invex.employees.domain.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateEmployeesServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private CreateEmployeesService service;

    @Test
    void createEmployees_throwsWhenListIsNull() {
        BusinessValidationException ex = assertThrows(
                BusinessValidationException.class,
                () -> service.createEmployees(null)
        );

        assertEquals("Employees cannot be empty", ex.getMessage());
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void createEmployees_throwsWhenListIsEmpty() {
        BusinessValidationException ex = assertThrows(
                BusinessValidationException.class,
                () -> service.createEmployees(List.of())
        );

        assertEquals("Employees cannot be empty", ex.getMessage());
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void createEmployees_throwsWhenEmployeeIsInvalid() {
        Employee invalid = Employee.builder().build();

        BusinessValidationException ex = assertThrows(
                BusinessValidationException.class,
                () -> service.createEmployees(List.of(invalid))
        );

        assertTrue(ex.getMessage().contains("First Name not valid"));
        assertTrue(ex.getMessage().contains("Middle Name not valid"));
        assertTrue(ex.getMessage().contains("Paternal Surname not valid"));
        assertTrue(ex.getMessage().contains("Maternal Surname not valid"));
        assertTrue(ex.getMessage().contains("Age not valid"));
        assertTrue(ex.getMessage().contains("Birth date not valid"));
        assertTrue(ex.getMessage().contains("Position not valid"));
        assertTrue(ex.getMessage().contains("Genre not valid"));
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void createEmployees_savesAllAndAssignsDefaultFields() {
        Employee first = validEmployee("Jose", "Hombre");
        Employee second = validEmployee("Ana", "Mujer");

        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> {
            Employee arg = invocation.getArgument(0);
            arg.setId(arg.getFirstName().equals("Jose") ? 1L : 2L);
            return arg;
        });

        List<Employee> result = service.createEmployees(List.of(first, second));

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository, times(2)).save(captor.capture());
        List<Employee> savedEmployees = captor.getAllValues();

        assertTrue(savedEmployees.get(0).getActive());
        assertNotNull(savedEmployees.get(0).getRegistrationDate());
        assertTrue(savedEmployees.get(1).getActive());
        assertNotNull(savedEmployees.get(1).getRegistrationDate());
    }

    private Employee validEmployee(String firstName, String genre) {
        return Employee.builder()
                .firstName(firstName)
                .middleName("Alberto")
                .paternalSurname("Espinosa")
                .maternalSurname("Valenzuela")
                .age(30)
                .genre(genre)
                .birthDate(new java.util.Date(1_000_000L))
                .position("Developer")
                .build();
    }
}
