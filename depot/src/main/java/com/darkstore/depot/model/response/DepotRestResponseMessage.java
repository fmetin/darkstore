package com.darkstore.depot.model.response;

import com.darkstore.depot.common.response.model.RestResponseMessage;

public class DepotRestResponseMessage extends RestResponseMessage {

    public final static String MSG_DPT_UNIQUE_DEPOT = "{depot.unique.depot}";
    public final static String MSG_DPT_UNIQUE_PRODUCT = "{depot.unique.product}";

    public final static String MSG_VALIDATION_CONSTRAINT_NOTNULL = "{depot.validation.constraint.NotNull.message}";
    public final static String MSG_VALIDATION_CONSTRAINT_DEPOT_NAME_NOTNULL = "{depot.validation.constraint.depot.name.NotNull.message}";
    public final static String MSG_VALIDATION_CONSTRAINT_TYPE_NOTNULL = "{depot.validation.constraint.type.NotNull.message}";
    public final static String MSG_VALIDATION_CONSTRAINT_CITY_NOTNULL = "{depot.validation.constraint.city.NotNull.message}";
    public final static String MSG_VALIDATION_CONSTRAINT_LATITUDE_NOTNULL = "{depot.validation.constraint.latitude.NotNull.message}";
    public final static String MSG_VALIDATION_CONSTRAINT_LONGITUDE_NOTNULL = "{depot.validation.constraint.longitude.NotNull.message}";
    public final static String MSG_VALIDATION_CONSTRAINT_STATUS_NOTNULL = "{depot.validation.constraint.status.NotNull.message}";
    public final static String MSG_VALIDATION_CONSTRAINT_COST_CENTER_NOTNULL = "{depot.validation.constraint.cost.center.NotNull.message}";
    public final static String MSG_VALIDATION_CONSTRAINT_LATITUDE_PATTERN = "{depot.validation.constraint.latitude.Pattern.message}";
    public final static String MSG_VALIDATION_CONSTRAINT_LONGITUDE_PATTERN = "{depot.validation.constraint.longitude.Pattern.message}";
    public final static String MSG_VALIDATION_CONSTRAINT_SIZE = "{depot.validation.constraint.Size.message}";
    public final static String MSG_VALIDATION_CONSTRAINT_DEPOT_NAME_SIZE = "{depot.validation.constraint.depot.name.Size.message}";
    public final static String MSG_VALIDATION_CONSTRAINT_COST_CENTER_SIZE = "{depot.validation.constraint.cost.center.Size.message}";
    public final static String MSG_DPT_MAIN_DEPOT_CANNOT_BE_MORE_THAN_ONE = "{depot.main.depot.cannot.be.more.than.one}";
    public final static String MSG_DPT_PRODUCT_ALREADY_EXIST = "{depot.product.already.exist}";
    public final static String MSG_DPT_STOCK_ONLY_CAN_BE_DEFINED_TO_MAIN_DEPOT = "{depot.stock.only.can.be.defined.to.main.depot}";
    public final static String MSG_DPT_DEPOT_DOESNT_EXIST = "{depot.depot.doesnt.exist}";
    public final static String MSG_DPT_DEPOT_CLOSED = "{depot.depot.closed}";
    public final static String MSG_DPT_PRODUCT_DOESNT_EXIST = "{depot.product.doesnt.exist}";
}
