package com.chukwuma.progresssoft.util;

import com.chukwuma.progresssoft.dto.ForexTransactionDetailsRequest;
import com.chukwuma.progresssoft.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;

import java.util.Currency;

@Slf4j
public abstract class ForexTransactionDetailsValidator {

    public static void validateForexTransactionDetailsDTO(ForexTransactionDetailsRequest transactionDetailsDTO){
        if(transactionDetailsDTO.getRecipientCurrencyISO().equalsIgnoreCase(transactionDetailsDTO.getSenderCurrencyISO())) {
            log.info("The recipient currency {} and sender currency \"{}\" is the same", transactionDetailsDTO.getRecipientCurrencyISO(), transactionDetailsDTO.getSenderCurrencyISO());
            throw new BadRequestException("The recipient currency and sender currency is the same");
        }

        if (validateCurrency(transactionDetailsDTO.getRecipientCurrencyISO())){
            log.info("Invalid recipient currency code, \"{}\"", transactionDetailsDTO.getRecipientCurrencyISO());
            throw new BadRequestException("Invalid recipient currency code");
        }

        if (validateCurrency(transactionDetailsDTO.getSenderCurrencyISO())) {
            log.info("Invalid sender currency code \"{}\"", transactionDetailsDTO.getSenderCurrencyISO());
            throw new BadRequestException("Invalid sender currency code");
        }
    }

    private static  boolean validateCurrency(String currencyCode) {
        return Currency.getAvailableCurrencies()
                .stream()
                .noneMatch(currency -> currency.getCurrencyCode().equalsIgnoreCase(currencyCode));
    }
}
