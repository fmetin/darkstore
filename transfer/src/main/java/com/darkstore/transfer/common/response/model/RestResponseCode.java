package com.darkstore.transfer.common.response.model;


import com.darkstore.transfer.common.util.Translator;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.darkstore.transfer.common.response.model.RestResponseMessage.*;


@Data
@AllArgsConstructor
public class RestResponseCode {

    private String responseCode;
    private String responseMessage;
    public static final RestResponseCode UNKNOWN_ERROR = new RestResponseCode("GNL-0001", MSG_UNKNOWN_ERROR);
    public static final RestResponseCode VALIDATION_ERROR = new RestResponseCode("GNL-0002", MSG_VALIDATION_ERROR);
    public static final RestResponseCode REDIS_LOCK_ERROR = new RestResponseCode("GNL-0003", MSG_REDIS_LOCK_ERROR);
    public String getlocalizedResponseMessage() {
        return Translator.toLocale(responseMessage);
    }
}
