package com.den.we;

import com.den.we.service.IPublishService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.MessageFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageRelayApplicationTests {

    @Resource
    private IPublishService service;

    @Test
    public void contextLoads() {
    }

    @Test
    public void mqttPublish() throws InterruptedException {
    }

    @Test
    public void mqttClientPublish() throws MqttException {
//        String broker = "tcp://localhost:1883";
//        String clientId = "relay";
//        //Use the memory persistence
//        MemoryPersistence persistence = new MemoryPersistence();
//
//        MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
//
//        MqttConnectOptions connOpts = new MqttConnectOptions();
//        connOpts.setCleanSession(true);
//        System.out.println("Connecting to broker:" + broker);
//        sampleClient.connect(connOpts);
//        System.out.println("Connected");
//
//        String topic = "friend_request";
//        String content = "hello king";
//        int qos = 2;
//        System.out.println("Publishing message:" + content);
//        MqttMessage message = new MqttMessage(content.getBytes());
//        message.setQos(qos);
//        sampleClient.publish(topic, message);
//        System.out.println("Message published");

    }

}
