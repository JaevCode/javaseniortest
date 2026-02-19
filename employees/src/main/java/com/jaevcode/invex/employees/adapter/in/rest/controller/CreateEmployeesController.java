package com.jaevcode.invex.employees.adapter.in.rest.controller;


import com.jaevcode.invex.employees.adapter.in.rest.dto.common.BaseResponse;
import com.jaevcode.invex.employees.adapter.in.rest.dto.createemployee.CreateEmployeeDto;
import com.jaevcode.invex.employees.adapter.in.rest.dto.createemployee.CreateEmployeeRequestDto;
import com.jaevcode.invex.employees.adapter.in.rest.mapper.EmployeeDtoMapper;
import com.jaevcode.invex.employees.application.port.in.CreateEmployeesUseCase;
import com.jaevcode.invex.employees.common.util.ResponseFactoryUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Create Employees", description = "Create employees service")
public class CreateEmployeesController {

    private final CreateEmployeesUseCase createEmployeesUseCase;
    private final EmployeeDtoMapper employeeDtoMapper;

    @PostMapping("/employees")
    @Operation(summary = "Create a employee", description = "Create employees")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employees created.",
                    content = @Content(schema = @Schema(implementation = List.class)))
    })
    public ResponseEntity<BaseResponse<List<CreateEmployeeDto>>> createEmployees(@RequestBody @Valid CreateEmployeeRequestDto requestDto) {
        List<CreateEmployeeDto> employees = createEmployeesUseCase
                .createEmployees(requestDto.employees().stream().map(employeeDtoMapper::createEmployeeDtoToModel).toList())
                .stream().map(employeeDtoMapper::modelToCreateEmployeeDto).toList();

        return ResponseFactoryUtil.createResponse(HttpStatus.CREATED, employees, "Created");
    }
}
