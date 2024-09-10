package io.codefresh.gradleexample.repository;

import io.codefresh.gradleexample.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}