package com.jaevcode.invex.employees.adapter.in.rest.controller;


import com.jaevcode.invex.employees.adapter.in.rest.dto.common.BaseResponse;
import com.jaevcode.invex.employees.adapter.in.rest.dto.common.EmployeeDto;
import com.jaevcode.invex.employees.adapter.in.rest.dto.updateemployee.UpdateEmployeeRequestDto;
import com.jaevcode.invex.employees.adapter.in.rest.mapper.EmployeeDtoMapper;
import com.jaevcode.invex.employees.application.port.in.UpdateEmployeeUseCase;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Update Employee", description = "Update employee service")
public class UpdateEmployeeController {

    private final UpdateEmployeeUseCase updateEmployeeUseCase;
    private final EmployeeDtoMapper employeeDtoMapper;

    @PutMapping("/employees/{id}")
    @Operation(summary = "Update employee", description = "Update employee")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employee updated.",
                    content = @Content(schema = @Schema(implementation = EmployeeDto.class)))
    })
    public ResponseEntity<BaseResponse<EmployeeDto>> updateEmployee(@PathVariable @Schema(description = "Employee id", example = "1") long id
            , @RequestBody UpdateEmployeeRequestDto request) {
        EmployeeDto employee = employeeDtoMapper.modelToDto(updateEmployeeUseCase
                .updateEmployee(id, employeeDtoMapper.updateEmployeeDtoToModel(request)));

        return ResponseFactoryUtil.createResponse(HttpStatus.ACCEPTED, employee, "Accepted");
    }
}
