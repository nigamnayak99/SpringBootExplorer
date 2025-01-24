package com.nigam.springbootexplorer.util;

import com.nigam.springbootexplorer.rest.RestResponse;

import java.util.function.Supplier;

public class Utility {

    public static RestResponse initializeRestResponse(String serviceReferenceId) {
        RestResponse restResponse = new RestResponse();
        restResponse.getMetaData().setServiceReferenceId(serviceReferenceId);
        return restResponse;
    }

    public static Supplier<Long> getCurrentSystemTimeInMillisSeconds = () -> System.currentTimeMillis();

}
