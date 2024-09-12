package io.codefresh.gradleexample.repository;

import io.codefresh.gradleexample.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TenderRepository extends JpaRepository<Tender, UUID> {

    // Метод для фильтрации по типу услуг
    List<Tender> findByServiceType(String serviceType);
    List<Tender> findAll();
    Optional<Tender> findById(UUID id);
    //save(Tender tender);
}