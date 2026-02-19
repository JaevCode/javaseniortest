package com.jaevcode.invex.employees.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "employees", schema = "interviewtest")
@Getter
@Setter
public class EmployeeJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fist_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "paternal_surname")
    private String paternalSurname;
    @Column(name = "maternal_surname")
    private String maternalSurname;
    private Integer age;
    private String genre;
    @Column(name = "birth_date")
    private Date birthDate;
    private String position;
    @Column(name = "registration_date")
    private Date registrationDate;
    private Boolean active;
}
