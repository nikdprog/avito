package io.codefresh.gradleexample.repository;

import io.codefresh.gradleexample.entity.Tender;
import io.codefresh.gradleexample.entity.enums.service_type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TenderRepository extends JpaRepository<Tender, UUID> {

    // Метод для фильтрации по типу услуг
    List<Tender> findByServiceType(service_type serviceType);
    List<Tender> findAll();
    Optional<Tender> findById(UUID id);
    Optional<Tender> findByOrganizationId(UUID id);

    @Override
    <S extends Tender> S save(S entity);
}