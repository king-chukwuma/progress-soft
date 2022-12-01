package com.chukwuma.progresssoft;

import com.chukwuma.progresssoft.dto.ForexTransactionDetailsRequest;

import java.math.BigDecimal;

public class TestUtil {

    public static ForexTransactionDetailsRequest createDTO(){
        return ForexTransactionDetailsRequest.builder()
                .amount(new BigDecimal(100))
                .recipientCurrencyISO("GBP")
                .senderCurrencyISO("USD")
                .build();
    }

    public static ForexTransactionDetailsRequest createDTO(BigDecimal amount, String recipientCurrency, String senderCurrency){
        return ForexTransactionDetailsRequest.builder()
                .amount(amount)
                .recipientCurrencyISO(recipientCurrency)
                .senderCurrencyISO(senderCurrency)
                .build();
    }
}
