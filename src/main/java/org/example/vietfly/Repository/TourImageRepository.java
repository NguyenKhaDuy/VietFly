package org.example.vietfly.Repository;

import org.example.vietfly.Entity.TourImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TourImageRepository extends JpaRepository<TourImageEntity, UUID> {
}
