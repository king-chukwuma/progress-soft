package com.chukwuma.progresssoft.dto;

import lombok.Data;

@Data
public class GenericResponse<R> {

    private int statusCode;
    private boolean success;
    private R body;

    public GenericResponse(int statusCode, boolean success, R body) {
        this.statusCode = statusCode;
        this.success = success;
        this.body = body;
    }
}
