package com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.repository;

import com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity.LostInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostInfoRepository extends JpaRepository<LostInfo, Long> {
}
