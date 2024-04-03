package com.vermeg.risk.repositories;

import com.vermeg.risk.entities.FinancialProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinancialProfileRepository extends JpaRepository<FinancialProfile, Long> {
    Optional<FinancialProfile> findByClientId(Long clientId);
}
