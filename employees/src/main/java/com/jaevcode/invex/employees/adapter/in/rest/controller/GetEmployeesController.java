package com.jaevcode.invex.employees.adapter.in.rest.controller;


import com.jaevcode.invex.employees.adapter.in.rest.dto.common.BaseResponse;
import com.jaevcode.invex.employees.adapter.in.rest.dto.common.EmployeeDto;
import com.jaevcode.invex.employees.adapter.in.rest.mapper.EmployeeDtoMapper;
import com.jaevcode.invex.employees.application.port.in.GetEmployeesUseCase;
import com.jaevcode.invex.employees.common.util.ResponseFactoryUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Get Employees", description = "Get employees service")
public class GetEmployeesController {

    private final GetEmployeesUseCase getEmployeesUseCase;
    private final EmployeeDtoMapper employeeDtoMapper;

    @GetMapping("/employees")
    @Operation(summary = "Get employees", description = "Get employees")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employees queried.",
                    content = @Content(schema = @Schema(implementation = List.class)))
    })
    public ResponseEntity<BaseResponse<List<EmployeeDto>>> getAllEmployees() {
        List<EmployeeDto> employees = getEmployeesUseCase.getAllEmployees()
                .stream().map(employeeDtoMapper::modelToDto).toList();

        return ResponseFactoryUtil.createResponse(HttpStatus.OK, employees, "Ok");
    }
}
