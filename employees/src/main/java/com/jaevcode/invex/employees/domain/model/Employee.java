package com.jaevcode.invex.employees.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    private Long id;
    private String firstName;
    private String middleName;
    private String paternalSurname;
    private String maternalSurname;
    private Integer age;
    private String genre;
    private Date birthDate;
    private String position;
    private Date registrationDate;
    private Boolean active;
}
