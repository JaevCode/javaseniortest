package com.jaevcode.invex.employees.adapter.out.persistence.repository;

import com.jaevcode.invex.employees.adapter.out.persistence.entity.EmployeeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaEmployeeSpringDataRepository extends JpaRepository<EmployeeJpaEntity, Long> {
    @Query("SELECT e FROM EmployeeJpaEntity e WHERE " +
            "LOWER(CONCAT(e.firstName, ' ', e.middleName, ' ', e.paternalSurname, ' ', e.maternalSurname)) " +
            "LIKE LOWER(CONCAT('%', :filter, '%'))")
    public List<EmployeeJpaEntity> findByFullName(@Param("filter") String filter);

}
