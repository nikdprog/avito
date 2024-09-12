package io.codefresh.gradleexample.repository;

import io.codefresh.gradleexample.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BidRepository extends JpaRepository<Bid, UUID> {

    Optional<Bid> findById(UUID id);
    Optional<Bid> findByAuthorId(UUID id);
    Optional<Bid> findByTenderId(UUID id);

    // Метод для фильтрации по типу услуг
    @Override
    <S extends Bid> S save(S entity);
}
