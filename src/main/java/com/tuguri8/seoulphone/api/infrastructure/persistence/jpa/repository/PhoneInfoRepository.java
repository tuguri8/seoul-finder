package com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.repository;

import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.PhoneInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PhoneInfoRepository extends JpaRepository<PhoneInfo, Long> {
    Optional<List<PhoneInfo>> findAllByFdYmdBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
