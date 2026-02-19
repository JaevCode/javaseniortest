package com.jaevcode.invex.employees.adapter.in.rest.controller;


import com.jaevcode.invex.employees.adapter.in.rest.dto.common.BaseResponse;
import com.jaevcode.invex.employees.adapter.in.rest.dto.common.EmployeeDto;
import com.jaevcode.invex.employees.application.port.in.DeleteEmployeeUseCase;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Delete Employee", description = "Delete employee service")
public class DeleteEmployeeController {

    private final DeleteEmployeeUseCase deleteEmployeeUseCase;

    @DeleteMapping("/employees/{id}")
    @Operation(summary = "Delete employee", description = "Delete employee")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employee deleted.",
                    content = @Content(schema = @Schema(implementation = EmployeeDto.class)))
    })
    public ResponseEntity<BaseResponse<Void>> updateEmployee(@PathVariable @Schema(description = "Employee id", example = "1") long id) {
        deleteEmployeeUseCase.deleteEmployee(id);
        return ResponseFactoryUtil.createResponse(HttpStatus.ACCEPTED, null, "Accepted");
    }
}
