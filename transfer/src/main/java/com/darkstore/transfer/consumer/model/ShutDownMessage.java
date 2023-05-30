package com.darkstore.transfer.consumer.model;

import lombok.Data;

import java.util.List;

@Data
public class ShutDownMessage {
    List<ShutDownInfo> shutDownInfoList;
}
