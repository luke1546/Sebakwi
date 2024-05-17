package com.ssafy.sebakwi.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttMessageGateway {

    void sendToMqtt(@Payload String payload, @Header(MqttHeaders.TOPIC) String topic);
}
