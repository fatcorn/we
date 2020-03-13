package com.den.we.service.impl;

import com.den.we.properties.MqttProperties;
import com.den.we.service.IPublishService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author fatKarin
 * @date 2019/10/31 9:28
 */
@Service
@Slf4j
public class PublishService implements IPublishService {

    @Resource
    private MqttProperties mqttProperties;


    @Override
    public Object publish()  {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic MWIzMGY0ZDQzYTY1ZjpNamt3TURjMk1EZzVNams0TXpNM01qRXpPRGM0TlRjNE16ZzJNREU0TmpNeE5qSQ==");
        Map<String, Object> params = new LinkedHashMap<>();
        //  也支持中文

        int i = 0;
        while (i < 200) {
            params.put("client_id", "relay");
            params.put("qos", 2);
            params.put("retain", true);
            params.put("topic", "friend_request");
            params.put("payload", "hello world!" + i);

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, headers);

            ResponseEntity response = restTemplate.postForEntity(mqttProperties.getUrl(), requestEntity, Object.class);
            System.out.println(response.getBody());
            i++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean tcpPublish(String topic, String payload) {

        try {
            String broker = "tcp://localhost:1883";
            String clientId = "relay_pub";
            //Use the memory persistence
            MemoryPersistence persistence = new MemoryPersistence();

            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);

            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false);
            System.out.println("Connecting to broker:" + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");

            int qos = 1;
            System.out.println("Publishing message:" + payload);
            MqttMessage message = new MqttMessage(payload.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");

            return true;
        } catch (MqttException e) {
            e.printStackTrace();
            log.error("MqttException:" + "message publish failed");
        }
        return false;
    }
}
