package com.chukwuma.progresssoft.controller;

import com.chukwuma.progresssoft.dto.ForexTransactionDetailsResponse;
import com.chukwuma.progresssoft.dto.GenericResponse;
import com.chukwuma.progresssoft.service.ForexTransactionDetailsService;
import com.chukwuma.progresssoft.dto.ForexTransactionDetailsRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/transaction")
public class ForexTransactionDetailsController {

    private final ForexTransactionDetailsService forexTransactionDetailsService;

    @PostMapping("save")
    public ResponseEntity<GenericResponse<ForexTransactionDetailsResponse>> saveTransaction(@RequestBody @Valid ForexTransactionDetailsRequest details){
        return new ResponseEntity<>(forexTransactionDetailsService.saveTransaction(details), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<GenericResponse<ForexTransactionDetailsResponse>> getTransaction(@PathVariable String id){
        return new ResponseEntity<>(forexTransactionDetailsService.getTransactionByTransactionId(id), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<GenericResponse<Page<ForexTransactionDetailsResponse>>> getAllTransactions(@PageableDefault Pageable pageable){
        return new ResponseEntity<>(forexTransactionDetailsService.getAllTransactions(pageable), HttpStatus.FOUND);
    }
}
