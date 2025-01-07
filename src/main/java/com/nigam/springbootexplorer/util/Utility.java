package com.nigam.springbootexplorer.util;

import com.nigam.springbootexplorer.rest.RestResponse;

public class Utility {

    public static RestResponse initializeRestResponse(String serviceReferenceId) {
        RestResponse restResponse = new RestResponse();
        restResponse.getMetaData().setServiceReferenceId(serviceReferenceId);
        return restResponse;
    }

}
