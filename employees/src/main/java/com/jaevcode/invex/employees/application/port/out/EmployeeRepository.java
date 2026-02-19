package com.jaevcode.invex.employees.application.port.out;

import com.jaevcode.invex.employees.domain.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    public List<Employee> getAll();
    public Optional<Employee> findById(Long id);
    public Employee save(Employee employee);
    public Employee update(Long id, Employee employee);
    public void deleteById(Long id);
    public List<Employee> findByName(String name);
}
