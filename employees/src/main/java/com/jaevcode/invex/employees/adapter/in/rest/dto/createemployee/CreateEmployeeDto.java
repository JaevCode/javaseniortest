package com.jaevcode.invex.employees.adapter.in.rest.dto.createemployee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateEmployeeDto(
        @Schema(description = "First Name of the employee", example = "Jose")
        @NotNull @NotEmpty String firstName,
        @Schema(description = "Middle Name of the employee", example = "Alberto")
        @NotNull @NotEmpty String middleName,
        @Schema(description = "Paternal Surname of the employee", example = "Espinosa")
        @NotNull @NotEmpty String paternalSurname,
        @Schema(description = "Maternal Surname of the employee", example = "Valenzuela")
        @NotNull @NotEmpty String maternalSurname,
        @Schema(description = "Age of the employee", example = "27")
        @NotNull Integer age,
        @Schema(description = "Genre of the employee", example = "Hombre")
        @NotNull @NotEmpty String genre,
        @Schema(description = "Birth date of the employee", example = "1998-11-27")
        @NotNull Date birthDate,
        @Schema(description = "Position of the employee", example = "Lider Tecnico Java")
        @NotNull @NotEmpty String position) {
}
