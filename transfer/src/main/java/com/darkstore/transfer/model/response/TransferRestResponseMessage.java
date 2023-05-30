package com.darkstore.transfer.model.response;

import com.darkstore.transfer.common.response.model.RestResponseMessage;

public class TransferRestResponseMessage extends RestResponseMessage {

    public final static String MSG_TRN_DEPOT_HAS_NOT_ENOUGH_STOCK = "{trn.depot.has.not.enough.stock}";
    public final static String MSG_TRN_INTERCITY_TRANSFERS_CAN_ONLY_BE_MADE_FROM_THE_MAIN_DEPOT = "{trn.intercity.transfers.can.only.be.made.from.the.main.depot}";
    public final static String MSG_TRN_DEPOT_CLIENT_ERROR = "{trn.depot.client.error}";
    public final static String MSG_TRN_INNER_CITY_TRANSFER_DISTANCE_ERROR = "{trn.inner.city.transfer.distance.error}";
    public final static String MSG_TRN_DEPOT_CLOSED = "{trn.depot.closed}";
    public final static String MSG_TRN_TRANSFER_RECORD_DOESNT_EXIST = "{trn.transfer.record.doesnt.exist}";
}
