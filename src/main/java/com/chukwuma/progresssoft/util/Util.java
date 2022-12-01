package com.chukwuma.progresssoft.util;

import com.chukwuma.progresssoft.dto.ForexTransactionDetailsRequest;
import com.chukwuma.progresssoft.dto.ForexTransactionDetailsResponse;
import com.chukwuma.progresssoft.entities.ForexTransactionDetails;

import java.util.UUID;

public abstract class Util {

    public static ForexTransactionDetailsResponse toTransactionResponse(ForexTransactionDetails forexTransactionDetails) {
        return ForexTransactionDetailsResponse.builder()
                .transactionId(forexTransactionDetails.getTransactionId())
                .amount(forexTransactionDetails.getAmount())
                .recipientCurrencyISO(forexTransactionDetails.getRecipientCurrencyISO())
                .senderCurrencyISO(forexTransactionDetails.getSenderCurrencyISO())
                .createdAt(forexTransactionDetails.getCreatedAt())
                .build();
    }

    public static ForexTransactionDetails createDealDetails(ForexTransactionDetailsRequest detailDTO) {
        ForexTransactionDetails forexTransactionDetails = new ForexTransactionDetails();
        forexTransactionDetails.setAmount(detailDTO.getAmount());
        forexTransactionDetails.setRecipientCurrencyISO(detailDTO.getRecipientCurrencyISO());
        forexTransactionDetails.setTransactionId(UUID.randomUUID());
        forexTransactionDetails.setSenderCurrencyISO(detailDTO.getSenderCurrencyISO());
        return forexTransactionDetails;
    }
}
