package com.nigam.springbootexplorer.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nigam.springbootexplorer.rest.exception.ServiceErrorInfo;
import lombok.*;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaData {

    private String serviceReferenceId;

    private Integer status;

    private String type;

    private String message;

    private Integer code;

    private ServiceErrorInfo serviceErrorInfo;

    public void setServiceReferenceId(String serviceReferenceId) {
        if (serviceReferenceId != null) {
            this.serviceReferenceId = serviceReferenceId;
        } else {
            this.serviceReferenceId = UUID.randomUUID().toString();
        }
    }

    public String getServiceReferenceId() {
        if (this.serviceReferenceId != null) {
            return serviceReferenceId;
        } else {
            return UUID.randomUUID().toString();
        }
    }

    public void setErrorOutCome(int status, String message, int code) {
        this.serviceReferenceId = getServiceReferenceId();
        this.status = status;
        this.type = "ERROR";
        this.message = message;
        this.code = code;
    }

    public void setSuccessOutCome() {
        this.serviceReferenceId = getServiceReferenceId();
        this.status = 200;
        this.message = "Success";
        this.code = 0;
    }
}
