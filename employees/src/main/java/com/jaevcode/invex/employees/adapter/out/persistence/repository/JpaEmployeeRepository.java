package com.jaevcode.invex.employees.adapter.out.persistence.repository;

import com.jaevcode.invex.employees.adapter.out.persistence.entity.EmployeeJpaEntity;
import com.jaevcode.invex.employees.adapter.out.persistence.mapper.EmployeeJpaMapper;
import com.jaevcode.invex.employees.application.port.out.EmployeeRepository;
import com.jaevcode.invex.employees.domain.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JpaEmployeeRepository implements EmployeeRepository {

    private final JpaEmployeeSpringDataRepository jpaEmployeeSpringDataRepository;
    private final EmployeeJpaMapper employeeJpaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        return jpaEmployeeSpringDataRepository.findAll().stream().map(employeeJpaMapper::jpaEntityToModel).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findById(Long id) {
        return jpaEmployeeSpringDataRepository.findById(id).map(employeeJpaMapper::jpaEntityToModel);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        EmployeeJpaEntity employeeJpaEntity = jpaEmployeeSpringDataRepository.save(employeeJpaMapper.modelToJpaEntity(employee));
        return employeeJpaMapper.jpaEntityToModel(employeeJpaEntity);
    }

    @Override
    @Transactional
    public Employee update(Long id, Employee employee) {
        EmployeeJpaEntity employeeJpaEntity = employeeJpaMapper.modelToJpaEntity(id, employee);
        EmployeeJpaEntity updatedEmployeeJpaEntity = jpaEmployeeSpringDataRepository.save(employeeJpaEntity);
        return employeeJpaMapper.jpaEntityToModel(updatedEmployeeJpaEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        jpaEmployeeSpringDataRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findByName(String name) {
        return jpaEmployeeSpringDataRepository.findByFullName(name).stream().map(employeeJpaMapper::jpaEntityToModel).toList();
    }
}
