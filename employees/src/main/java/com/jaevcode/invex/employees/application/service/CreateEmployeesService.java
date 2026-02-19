package com.jaevcode.invex.employees.application.service;

import com.jaevcode.invex.employees.application.port.in.CreateEmployeesUseCase;
import com.jaevcode.invex.employees.application.port.out.EmployeeRepository;
import com.jaevcode.invex.employees.common.exception.BusinessValidationException;
import com.jaevcode.invex.employees.domain.model.Employee;
import com.jaevcode.invex.employees.domain.model.Genre;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class CreateEmployeesService implements CreateEmployeesUseCase {

    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> createEmployees(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            throw new BusinessValidationException("Employees cannot be empty");
        }

        List<Employee> newEmployees = new ArrayList<>();

        for (Employee employee : employees) {
            validateEmployee(employee);
        }
        for (Employee employee : employees) {
            employee.setActive(true);
            employee.setRegistrationDate(new Date());
            newEmployees.add(employeeRepository.save(employee));
        }

        return newEmployees;
    }

    private void validateEmployee(Employee employee) throws BusinessValidationException {
        List<String> errors = new ArrayList<>();

        if (employee.getFirstName() == null || employee.getFirstName().isEmpty()) errors.add("First Name not valid");
        if (employee.getMiddleName() == null || employee.getMiddleName().isEmpty()) errors.add("Middle Name not valid");
        if (employee.getPaternalSurname() == null || employee.getPaternalSurname().isEmpty())
            errors.add("Paternal Surname not valid");
        if (employee.getMaternalSurname() == null || employee.getMaternalSurname().isEmpty())
            errors.add("Maternal Surname not valid");
        if (employee.getAge() == null) errors.add("Age not valid");
        if (employee.getBirthDate() == null) errors.add("Birth date not valid");
        if (employee.getPosition() == null || employee.getPosition().isEmpty()) errors.add("Position not valid");

        if(!Genre.isValidGenre(employee.getGenre()))errors.add("Genre not valid");

        if (!errors.isEmpty()) throw new BusinessValidationException(String.join(", ", errors));
    }
}
