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

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateEmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private UpdateEmployeeService service;

    @Test
    void updateEmployee_throwsWhenEmployeeNotFound() {
        when(employeeRepository.findById(8L)).thenReturn(Optional.empty());

        BusinessValidationException ex = assertThrows(
                BusinessValidationException.class,
                () -> service.updateEmployee(8L, Employee.builder().build())
        );

        assertEquals("Employee with id 8 not found", ex.getMessage());
        verify(employeeRepository).findById(8L);
        verify(employeeRepository, never()).update(anyLong(), any(Employee.class));
    }

    @Test
    void updateEmployee_usesCurrentValuesWhenIncomingFieldsAreNullOrEmpty() {
        Employee current = baseCurrentEmployee();
        Employee request = Employee.builder()
                .firstName("")
                .middleName(null)
                .paternalSurname("")
                .maternalSurname(null)
                .age(null)
                .genre(null)
                .birthDate(null)
                .position("")
                .active(null)
                .build();

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(current));
        when(employeeRepository.update(eq(1L), any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(1));

        Employee result = service.updateEmployee(1L, request);

        assertEquals(current.getFirstName(), result.getFirstName());
        assertEquals(current.getMiddleName(), result.getMiddleName());
        assertEquals(current.getPaternalSurname(), result.getPaternalSurname());
        assertEquals(current.getMaternalSurname(), result.getMaternalSurname());
        assertEquals(current.getAge(), result.getAge());
        assertEquals(current.getGenre(), result.getGenre());
        assertEquals(current.getBirthDate(), result.getBirthDate());
        assertEquals(current.getPosition(), result.getPosition());
        assertEquals(current.getActive(), result.getActive());
    }

    @Test
    void updateEmployee_mergesNewValuesAndAcceptsValidGenre() {
        Employee current = baseCurrentEmployee();
        Date newBirthDate = new Date(3_000_000L);
        Employee request = Employee.builder()
                .firstName("Juan")
                .middleName("Carlos")
                .paternalSurname("Lopez")
                .maternalSurname("Martinez")
                .age(22)
                .genre("Mujer")
                .birthDate(newBirthDate)
                .position("Architect")
                .active(false)
                .build();

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(current));
        when(employeeRepository.update(eq(1L), any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(1));

        Employee result = service.updateEmployee(1L, request);

        assertEquals("Juan", result.getFirstName());
        assertEquals("Carlos", result.getMiddleName());
        assertEquals("Lopez", result.getPaternalSurname());
        assertEquals("Martinez", result.getMaternalSurname());
        assertEquals(22, result.getAge());
        assertEquals("Mujer", result.getGenre());
        assertEquals(newBirthDate, result.getBirthDate());
        assertEquals("Architect", result.getPosition());
        assertFalse(result.getActive());

        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).update(eq(1L), captor.capture());
        assertEquals("Mujer", captor.getValue().getGenre());
    }

    @Test
    void updateEmployee_throwsWhenGenreIsInvalid() {
        Employee current = baseCurrentEmployee();
        Employee request = Employee.builder().genre("INVALID").build();

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(current));

        BusinessValidationException ex = assertThrows(
                BusinessValidationException.class,
                () -> service.updateEmployee(1L, request)
        );

        assertEquals("Genre not valid", ex.getMessage());
        verify(employeeRepository, never()).update(anyLong(), any(Employee.class));
    }

    private Employee baseCurrentEmployee() {
        return Employee.builder()
                .id(1L)
                .firstName("Jose")
                .middleName("Alberto")
                .paternalSurname("Espinosa")
                .maternalSurname("Valenzuela")
                .age(30)
                .genre("Hombre")
                .birthDate(new Date(1_000_000L))
                .position("Developer")
                .active(true)
                .build();
    }
}
