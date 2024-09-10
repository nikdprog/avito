package io.codefresh.gradleexample.repository;

import io.codefresh.gradleexample.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TenderRepository extends JpaRepository<Tender, Long> {

    // Метод для фильтрации по типу услуг
    List<Tender> findByServiceType(String serviceType);
    List<Tender> findAll();
    Optional<Tender> findById(Long id);
    //save(Tender tender);
}