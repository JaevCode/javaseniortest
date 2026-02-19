package com.jaevcode.invex.employees.adapter.in.rest.mapper;

import com.jaevcode.invex.employees.adapter.in.rest.dto.common.EmployeeDto;
import com.jaevcode.invex.employees.adapter.in.rest.dto.createemployee.CreateEmployeeDto;
import com.jaevcode.invex.employees.adapter.in.rest.dto.updateemployee.UpdateEmployeeRequestDto;
import com.jaevcode.invex.employees.domain.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeDtoMapper {

    @Mapping(target = "id", source = "id")
    EmployeeDto modelToDto(long id, Employee employee);

    EmployeeDto modelToDto(Employee employee);

    Employee dtoToModel(EmployeeDto employeeDto);

    CreateEmployeeDto modelToCreateEmployeeDto(Employee employee);

    Employee createEmployeeDtoToModel(CreateEmployeeDto createEmployeeDto);

    UpdateEmployeeRequestDto modelToUpdateEmployeeDto(Employee employee);

    Employee updateEmployeeDtoToModel(UpdateEmployeeRequestDto updateEmployeeDto);
}
