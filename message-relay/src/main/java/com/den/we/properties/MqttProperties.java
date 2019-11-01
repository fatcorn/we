package com.den.we.properties;

import lombok.Data;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.text.MessageFormat;

/**
 * @author fatKarin
 * @date 2019/10/31 12:15
 */
@Data
@ConfigurationProperties(prefix = "mqtt")
@Configuration
public class MqttProperties {

    private String url;

}
