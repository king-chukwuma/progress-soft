package com.chukwuma.progresssoft.repository;

import com.chukwuma.progresssoft.entities.ForexTransactionDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ForexTransactionDetailsRepository extends JpaRepository<ForexTransactionDetails, Long> {

    Optional<ForexTransactionDetails> findByTransactionId(UUID transactionId);

    Page<ForexTransactionDetails> findAll(Pageable pageable);

}
