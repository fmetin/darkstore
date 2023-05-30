package com.darkstore.depot.producer;

import com.darkstore.depot.producer.model.ShutDownInfo;
import com.darkstore.depot.producer.model.ShutDownMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShutDownProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.shutDown.routing.key}")
    private String shutDownRoutingKey;


    private RabbitTemplate rabbitTemplate;

    public ShutDownProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(ShutDownMessage message){
        log.info(String.format("Json message sent -> %s", message.toString()));
        rabbitTemplate.convertAndSend(exchange, shutDownRoutingKey, message);
    }

}