package com.darkstore.depot.model.response;

import com.darkstore.depot.common.response.model.RestResponseCode;

public class DepotRestResponseCode extends RestResponseCode {
    public DepotRestResponseCode(String responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public static final RestResponseCode DPT_UNIQUE_DEPOT = new RestResponseCode("DPT-0001", DepotRestResponseMessage.MSG_DPT_UNIQUE_DEPOT);
    public static final RestResponseCode DPT_MAIN_DEPOT_CANNOT_BE_MORE_THAN_ONE = new RestResponseCode("DPT-0002", DepotRestResponseMessage.MSG_DPT_MAIN_DEPOT_CANNOT_BE_MORE_THAN_ONE);
    public static final RestResponseCode DPT_PRODUCT_ALREADY_EXIST = new RestResponseCode("DPT-0003", DepotRestResponseMessage.MSG_DPT_PRODUCT_ALREADY_EXIST);
    public static final RestResponseCode DPT_STOCK_ONLY_CAN_BE_DEFINED_TO_MAIN_DEPOT = new RestResponseCode("DPT-0004", DepotRestResponseMessage.MSG_DPT_STOCK_ONLY_CAN_BE_DEFINED_TO_MAIN_DEPOT);
    public static final RestResponseCode DPT_DEPOT_DOESNT_EXIST = new RestResponseCode("DPT-0005", DepotRestResponseMessage.MSG_DPT_DEPOT_DOESNT_EXIST);
    public static final RestResponseCode DPT_DEPOT_CLOSED = new RestResponseCode("DPT-0006", DepotRestResponseMessage.MSG_DPT_DEPOT_CLOSED);
    public static final RestResponseCode DPT_PRODUCT_DOESNT_EXIST = new RestResponseCode("DPT-0007", DepotRestResponseMessage.MSG_DPT_PRODUCT_DOESNT_EXIST);
    public static final RestResponseCode DPT_STOCK_CANNOT_BE_LOWER_THAN_ZERO = new RestResponseCode("DPT-0008", DepotRestResponseMessage.MSG_DPT_STOCK_CANNOT_BE_LOWER_THAN_ZERO);
}
