package io.codefresh.gradleexample.repository;

import io.codefresh.gradleexample.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByUsername(String creatorUsername);
}