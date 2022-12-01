package com.chukwuma.progresssoft.controller;

import com.chukwuma.progresssoft.ProgressSoftApplication;
import com.chukwuma.progresssoft.TestUtil;
import com.chukwuma.progresssoft.dto.ForexTransactionDetailsRequest;
import com.chukwuma.progresssoft.entities.ForexTransactionDetails;
import com.chukwuma.progresssoft.repository.ForexTransactionDetailsRepository;
import com.chukwuma.progresssoft.service.ForexTransactionDetailsService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

@AutoConfigureMockMvc
@SpringBootTest(classes = ProgressSoftApplication.class)
@RequiredArgsConstructor
public class ForexTransactionDetailsControllerTest {

    @Autowired
    private ForexTransactionDetailsRepository forexTransactionDetailsRepository;

    @Autowired
    private ForexTransactionDetailsService forexTransactionDetailsService;

    @Autowired
    private MockMvc mockMvc;

    private final static long DEFAULT_ID = 1L;
    private final static BigDecimal DEFAULT_AMOUNT = new BigDecimal(100);
    private final static String DEFAULT_SENDER_CURRENCY = Currency.getInstance(Locale.US).getCurrencyCode();
    private final static String DEFAULT_RECIPIENT_CURRENCY = Currency.getInstance(Locale.UK).getCurrencyCode();
    private final static Timestamp DEFAULT_CREATE_TIME = Timestamp.valueOf(LocalDateTime.now());
    private final static ObjectMapper MAPPER = createObjectMapper();

    private ForexTransactionDetails forexTransactionDetails;

    @BeforeEach
    public void init(){
        forexTransactionDetails = createForexTransaction();
    }

    @AfterEach
    public void clearRepository(){
        forexTransactionDetailsRepository.deleteAll();
    }

    private static  ObjectMapper createObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    private ForexTransactionDetails createForexTransaction(){
        ForexTransactionDetails transactionDetails = new ForexTransactionDetails();
        transactionDetails.setId(DEFAULT_ID);
        transactionDetails.setAmount(DEFAULT_AMOUNT);
        transactionDetails.setRecipientCurrencyISO(DEFAULT_RECIPIENT_CURRENCY);
        transactionDetails.setSenderCurrencyISO(DEFAULT_SENDER_CURRENCY);
        transactionDetails.setCreatedAt(DEFAULT_CREATE_TIME);

        return transactionDetails;
    }

    @Test
    @Transactional
    void createForexDealTest() throws Exception {
        int dbSize = forexTransactionDetailsRepository.findAll().size();
        ForexTransactionDetailsRequest dto = TestUtil.createDTO(DEFAULT_AMOUNT,DEFAULT_RECIPIENT_CURRENCY, DEFAULT_SENDER_CURRENCY);

        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/v1/transaction/save")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(MAPPER.writeValueAsBytes(dto)));

        List<ForexTransactionDetails> allTransactions = forexTransactionDetailsRepository.findAll();
        ForexTransactionDetails createdTransactionDetails = allTransactions.get(allTransactions.size() - 1);

        Assertions.assertThat(allTransactions).hasSize(dbSize + 1);
        Assertions.assertThat(createdTransactionDetails.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        Assertions.assertThat(createdTransactionDetails.getRecipientCurrencyISO()).isEqualTo(DEFAULT_RECIPIENT_CURRENCY);
        Assertions.assertThat(createdTransactionDetails.getSenderCurrencyISO()).isEqualTo(DEFAULT_SENDER_CURRENCY);
    }
}
