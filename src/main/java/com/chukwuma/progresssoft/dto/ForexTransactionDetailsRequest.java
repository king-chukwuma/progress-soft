package com.chukwuma.progresssoft.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
public class ForexTransactionDetailsRequest {
    @NonNull
    @NotBlank(message = "Recipient currency cannot be blank")
    @Pattern(regexp = "^[A-Za-z]{3}$", message = "Currency can only be letters")
    private String recipientCurrencyISO;

    @NonNull
    @NotBlank(message = "Sender currency cannot be blank")
    @Pattern(regexp = "^[A-Za-z]{3}$", message = "Currency can only be letters")
    private String senderCurrencyISO;

    @NonNull
    @Min(value = 1, message = "Transaction amount cannot be less than 1.00")
    private BigDecimal amount;

    public ForexTransactionDetailsRequest() {
    }

    public ForexTransactionDetailsRequest(String recipientCurrencyISO, String senderCurrencyISO, BigDecimal amount) {
        this.recipientCurrencyISO = recipientCurrencyISO;
        this.senderCurrencyISO = senderCurrencyISO;
        this.amount = amount;
    }
}
