package com.jaevcode.invex.employees.application.service;

import com.jaevcode.invex.employees.application.port.in.UpdateEmployeeUseCase;
import com.jaevcode.invex.employees.application.port.out.EmployeeRepository;
import com.jaevcode.invex.employees.common.exception.BusinessValidationException;
import com.jaevcode.invex.employees.domain.model.Employee;
import com.jaevcode.invex.employees.domain.model.Genre;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class UpdateEmployeeService implements UpdateEmployeeUseCase {
    private EmployeeRepository employeeRepository;

    @Override
    public Employee updateEmployee(long id, Employee employee) {
        Employee currEmployee = employeeRepository.findById(id).orElseThrow(() -> new BusinessValidationException("Employee with id " + id + " not found"));

        String firstName = getNewTextOrCurrent(employee.getFirstName(), currEmployee.getFirstName());
        String middleName = getNewTextOrCurrent(employee.getMiddleName(), currEmployee.getMiddleName());
        String paternalSurname = getNewTextOrCurrent(employee.getPaternalSurname(), currEmployee.getPaternalSurname());
        String maternalSurname = getNewTextOrCurrent(employee.getMaternalSurname(), currEmployee.getMaternalSurname());
        Integer age = getNewIntOrCurrent(employee.getAge(), currEmployee.getAge());
        String genre = getNewGenreOrCurrent(employee.getGenre(), currEmployee.getGenre());
        Date birthDate = getNewDateOrCurrent(employee.getBirthDate(), currEmployee.getBirthDate());
        String position = getNewTextOrCurrent(employee.getPosition(), currEmployee.getPosition());
        Boolean active = getNewBooleanOrCurrent(employee.getActive(), currEmployee.getActive());

        return employeeRepository.update(id, Employee.builder()
                .firstName(firstName)
                .middleName(middleName)
                .paternalSurname(paternalSurname)
                .maternalSurname(maternalSurname)
                .age(age)
                .genre(genre)
                .birthDate(birthDate)
                .position(position)
                .active(active)
                .build());
    }

    private String getNewTextOrCurrent(String newText, String currentText) {
        return newText != null && !newText.isEmpty() ? newText : currentText;
    }

    private Integer getNewIntOrCurrent(Integer newNumber, Integer currentNumber) {
        return newNumber != null ? newNumber : currentNumber;
    }

    private String getNewGenreOrCurrent(String newGenre, String currentGenre) throws BusinessValidationException {
        if (newGenre != null) {
            if (Genre.isValidGenre(newGenre)) {
                return newGenre;
            } else {
                throw new BusinessValidationException("Genre not valid");
            }
        } else {
            return currentGenre;
        }
    }

    private Date getNewDateOrCurrent(Date newDate, Date currentDate) {
        return newDate != null ? newDate : currentDate;
    }

    private Boolean getNewBooleanOrCurrent(Boolean newBoolean, Boolean currentBoolean) {
        return newBoolean != null ? newBoolean : currentBoolean;
    }
}
