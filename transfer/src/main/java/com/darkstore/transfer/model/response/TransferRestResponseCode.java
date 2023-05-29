package com.darkstore.transfer.model.response;

import com.darkstore.transfer.common.response.model.RestResponseCode;

public class TransferRestResponseCode extends RestResponseCode {
    public TransferRestResponseCode(String responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public static final RestResponseCode DPT_UNIQUE_DEPOT = new RestResponseCode("DPT-0001", TransferRestResponseMessage.MSG_DPT_UNIQUE_DEPOT);

}
