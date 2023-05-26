package com.darkstore.depot.model.response;

import com.darkstore.depot.common.response.model.RestResponseCode;

public class DepotRestResponseCode extends RestResponseCode {
    public DepotRestResponseCode(String responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public static final RestResponseCode DPT_UNKNOWN_ERROR = new RestResponseCode("DPT-0001", DepotRestResponseMessage.MSG_DPT_UNKNOWN_ERROR);
}
