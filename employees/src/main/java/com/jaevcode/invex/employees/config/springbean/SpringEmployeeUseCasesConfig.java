package com.jaevcode.invex.employees.config.springbean;

import com.jaevcode.invex.employees.application.port.in.*;
import com.jaevcode.invex.employees.application.port.out.EmployeeRepository;
import com.jaevcode.invex.employees.application.service.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SpringEmployeeUseCasesConfig {

    private EmployeeRepository employeeRepository;

    @Bean
    GetEmployeesUseCase getEmployeesUseCase() {
        return new GetEmployeesService(employeeRepository);
    }

    @Bean
    GetEmployeeByIdUseCase getEmployeeByIdUseCase() {
        return new GetEmployeeByIdService(employeeRepository);
    }

    @Bean
    CreateEmployeesUseCase createEmployeeUseCase() {
        return new CreateEmployeesService(employeeRepository);
    }

    @Bean
    UpdateEmployeeUseCase updateEmployeeUseCase() {
        return new UpdateEmployeeService(employeeRepository);
    }

    @Bean
    DeleteEmployeeUseCase deleteEmployeeUseCase() {
        return new DeleteEmployeeService(employeeRepository);
    }

    @Bean
    SearchEmployeeByNameUseCase searchEmployeeByNameUseCase() {
        return new SearchEmployeeByNameService(employeeRepository);
    }

}
