package com.jaevcode.invex.employees.adapter.in.rest.dto.createemployee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateEmployeeRequestDto(@Schema(description = "Employees", example = "[]")
                                       @NotNull @NotEmpty List<@Valid CreateEmployeeDto> employees) {
}
