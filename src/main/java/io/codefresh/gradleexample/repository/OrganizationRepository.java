package io.codefresh.gradleexample.repository;

import io.codefresh.gradleexample.entity.Employee;
import io.codefresh.gradleexample.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {


    Optional<Organization> findById(Long aLong);
}