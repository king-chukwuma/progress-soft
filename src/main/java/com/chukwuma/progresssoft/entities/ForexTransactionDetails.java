package com.chukwuma.progresssoft.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "deal_details")
public class ForexTransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID transactionId;

    private String recipientCurrencyISO;

    private String senderCurrencyISO;

    private BigDecimal amount;

    @Column
    private Timestamp createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = Timestamp.from(Calendar.getInstance().toInstant());
    }
}
