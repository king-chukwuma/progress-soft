package com.chukwuma.progresssoft.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
public class GenericExceptionResponse {
    private int statusCode;
    private boolean success;
    private Object message;
    private Timestamp timestamp;
}
