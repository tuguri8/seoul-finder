package com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.repository;

import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.LostInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LostInfoRepository extends JpaRepository<LostInfo, Long> {
    Optional<List<LostInfo>> findAllByCategoryAndFdYmdBetween(String category, LocalDate startDate, LocalDate endDate, Pageable pageable);
    Long countByCategoryAndFdYmdBetween(String category, LocalDate startDate, LocalDate endDate);
    Optional<List<LostInfo>> findAllByCategoryAndFdPrdtNmContaining(String category, String name, Pageable pageable);
    Long countByCategoryAndFdPrdtNmContaining(String category, String name);
}
