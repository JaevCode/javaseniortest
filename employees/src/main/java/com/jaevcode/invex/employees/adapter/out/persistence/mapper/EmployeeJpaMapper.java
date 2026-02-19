package com.jaevcode.invex.employees.adapter.out.persistence.mapper;

import com.jaevcode.invex.employees.adapter.out.persistence.entity.EmployeeJpaEntity;
import com.jaevcode.invex.employees.domain.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeJpaMapper {

    @Mapping(target = "id", source = "id")
    EmployeeJpaEntity modelToJpaEntity(long id, Employee employee);

    EmployeeJpaEntity modelToJpaEntity(Employee employee);

    Employee jpaEntityToModel(EmployeeJpaEntity employeeJpaEntity);
}
