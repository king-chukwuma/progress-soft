package com.chukwuma.progresssoft.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Data
public class ForexTransactionDetailsResponse {
    private UUID transactionId;

    private String recipientCurrencyISO;

    private String senderCurrencyISO;

    private BigDecimal amount;

    private Timestamp createdAt;
}
