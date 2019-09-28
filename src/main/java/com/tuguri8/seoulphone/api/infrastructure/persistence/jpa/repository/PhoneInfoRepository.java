package com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.repository;

import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.PhoneInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneInfoRepository extends JpaRepository<PhoneInfo, Long> {
}
