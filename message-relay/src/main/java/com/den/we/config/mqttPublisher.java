package com.den.we.config;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fatKarin
 * @date 2019/11/1 11:24
 */
@Configuration
public class mqttPublisher {

    @Bean
    public MqttClient mqttClientTemplate() throws MqttException {
        String broker = "tcp://localhost:1883";
        String clientId = "relay_pub";
        //Use the memory persistence
        MemoryPersistence persistence = new MemoryPersistence();

        MqttClient mqttClient = new MqttClient(broker, clientId, persistence);

        return mqttClient;
    }
}
