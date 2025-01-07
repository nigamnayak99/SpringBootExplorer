package com.nigam.springbootexplorer.rest.exception;

import lombok.Data;

@Data
public class ServiceErrorInfo {

    private String errorMessage;

    private Integer errorCode;
}
