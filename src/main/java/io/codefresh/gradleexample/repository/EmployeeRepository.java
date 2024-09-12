package io.codefresh.gradleexample.repository;

import io.codefresh.gradleexample.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByUsername(String creatorUsername);


    Optional<Employee> findById(UUID id);
}