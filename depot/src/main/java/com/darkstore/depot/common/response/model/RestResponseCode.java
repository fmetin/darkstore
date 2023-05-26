package com.darkstore.depot.common.response.model;


import com.darkstore.depot.common.util.Translator;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.darkstore.depot.common.response.model.RestResponseMessage.MSG_UNKNOWN_ERROR;
import static com.darkstore.depot.common.response.model.RestResponseMessage.MSG_VALIDATION_ERROR;


@Data
@AllArgsConstructor
public class RestResponseCode {

    private String responseCode;
    private String responseMessage;
    public static final RestResponseCode UNKNOWN_ERROR = new RestResponseCode("GNL-0001", MSG_UNKNOWN_ERROR);
    public static final RestResponseCode VALIDATION_ERROR = new RestResponseCode("GNL-0002", MSG_VALIDATION_ERROR);
    public String getlocalizedResponseMessage() {
        return Translator.toLocale(responseMessage);
    }
}
