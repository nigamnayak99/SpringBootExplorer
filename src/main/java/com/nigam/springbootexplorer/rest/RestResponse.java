package com.nigam.springbootexplorer.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@ToString
@AllArgsConstructor
public class RestResponse extends ServiceResponse {

    Object responseData;

    public RestResponse() {
        super();
    }
}
