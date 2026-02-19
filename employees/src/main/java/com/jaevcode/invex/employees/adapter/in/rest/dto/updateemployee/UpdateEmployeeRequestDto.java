package com.jaevcode.invex.employees.adapter.in.rest.dto.updateemployee;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public record UpdateEmployeeRequestDto(
        @Schema(description = "First Name of the employee", example = "Jose")
        String firstName,
        @Schema(description = "Middle Name of the employee", example = "Alberto")
        String middleName,
        @Schema(description = "Paternal Surname of the employee", example = "Espinosa")
        String paternalSurname,
        @Schema(description = "Maternal Surname of the employee", example = "Valenzuela")
        String maternalSurname,
        @Schema(description = "Age of the employee", example = "27")
        Integer age,
        @Schema(description = "Genre of the employee", example = "Hombre")
        String genre,
        @Schema(description = "Birth date of the employee", example = "1998-11-27")
        Date birthDate,
        @Schema(description = "Position of the employee", example = "Lider Tecnico Java")
        String position,
        @Schema(description = "Status of the employee", example = "true")
        Boolean active) {
}
