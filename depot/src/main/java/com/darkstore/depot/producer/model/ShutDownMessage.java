package com.darkstore.depot.producer.model;

import lombok.Data;

import java.util.List;

@Data
public class ShutDownMessage {
    List<ShutDownInfo> shutDownInfoList;
}
