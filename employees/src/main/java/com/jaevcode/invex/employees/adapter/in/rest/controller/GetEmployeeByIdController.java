package com.jaevcode.invex.employees.adapter.in.rest.controller;


import com.jaevcode.invex.employees.adapter.in.rest.dto.common.BaseResponse;
import com.jaevcode.invex.employees.adapter.in.rest.dto.common.EmployeeDto;
import com.jaevcode.invex.employees.adapter.in.rest.mapper.EmployeeDtoMapper;
import com.jaevcode.invex.employees.application.port.in.GetEmployeeByIdUseCase;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Get Employee by id", description = "Get employee by id service")
public class GetEmployeeByIdController {

    private final GetEmployeeByIdUseCase getEmployeeByIdUseCase;
    private final EmployeeDtoMapper employeeDtoMapper;

    @GetMapping("/employees/{id}")
    @Operation(summary = "Get employee by id", description = "Get employee by id")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employee queried.",
                    content = @Content(schema = @Schema(implementation = EmployeeDto.class)))
    })
    public ResponseEntity<BaseResponse<EmployeeDto>> getEmployeeById(@PathVariable @Schema(description = "Employee id", example = "1") long id) {
        EmployeeDto employee = employeeDtoMapper.modelToDto(getEmployeeByIdUseCase.getEmployeeById(id));

        return ResponseFactoryUtil.createResponse(HttpStatus.OK, employee, "Ok");
    }
}
