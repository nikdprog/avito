package io.codefresh.gradleexample.repository;

import io.codefresh.gradleexample.entity.Employee;
import io.codefresh.gradleexample.entity.Organization;
import io.codefresh.gradleexample.entity.Organization_responsible;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrganizationResponsibleRepository extends JpaRepository<Organization_responsible, UUID> {


    Optional<Organization_responsible> findByEmployee_id(UUID id);

}