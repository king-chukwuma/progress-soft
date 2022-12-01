package com.chukwuma.progresssoft.service;

import com.chukwuma.progresssoft.dto.ForexTransactionDetailsResponse;
import com.chukwuma.progresssoft.dto.GenericResponse;
import com.chukwuma.progresssoft.entities.ForexTransactionDetails;
import com.chukwuma.progresssoft.exception.NotFoundException;
import com.chukwuma.progresssoft.repository.ForexTransactionDetailsRepository;
import com.chukwuma.progresssoft.util.ForexTransactionDetailsValidator;
import com.chukwuma.progresssoft.util.Util;
import com.chukwuma.progresssoft.dto.ForexTransactionDetailsRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ForexTransactionDetailsService {

    private final ForexTransactionDetailsRepository forexTransactionDetailsRepository;

    @Transactional
    public GenericResponse<ForexTransactionDetailsResponse> saveTransaction(ForexTransactionDetailsRequest forexTransactionDetailsRequest) {
        ForexTransactionDetailsValidator.validateForexTransactionDetailsDTO(forexTransactionDetailsRequest);
        ForexTransactionDetails forexTransactionDetails = forexTransactionDetailsRepository.save(Util.createDealDetails(forexTransactionDetailsRequest));
        log.info("Successfully saved transaction");

        return new GenericResponse<>(HttpStatus.CREATED.value(), true, Util.toTransactionResponse(forexTransactionDetails));
    }

    public GenericResponse<ForexTransactionDetailsResponse> getTransactionByTransactionId(String id) {
        ForexTransactionDetails forexTransactionDetails = forexTransactionDetailsRepository.findByTransactionId(UUID.fromString(id)).orElseThrow(() -> new NotFoundException("Transaction not Found"));
        System.out.println(UUID.fromString(id));
        return new GenericResponse<>(HttpStatus.FOUND.value(), true, Util.toTransactionResponse(forexTransactionDetails));
    }

    public GenericResponse<Page<ForexTransactionDetailsResponse>> getAllTransactions(Pageable pageable) {
        Page<ForexTransactionDetailsResponse> allTransactions = forexTransactionDetailsRepository.findAll(pageable).map(Util::toTransactionResponse);
        return new GenericResponse<>(HttpStatus.FOUND.value(), true, allTransactions);
    }
}
