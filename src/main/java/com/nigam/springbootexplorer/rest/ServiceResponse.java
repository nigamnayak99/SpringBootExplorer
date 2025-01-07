package com.nigam.springbootexplorer.rest;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@AllArgsConstructor
public class ServiceResponse {

    private MetaData metaData;

    public ServiceResponse() {
        this.metaData = new MetaData();
    }
}
