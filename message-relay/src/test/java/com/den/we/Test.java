package com.den.we;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Test {

    public static void main(String args[]) throws  Exception{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        String broker = "tcp://localhost:1883";
                        String clientId = "relay_pub";
                        //Use the memory persistence
                        MemoryPersistence persistence = new MemoryPersistence();

                        MqttClient sampleClient = new MqttClient(broker, clientId, persistence);

                        MqttConnectOptions connOpts = new MqttConnectOptions();
                        connOpts.setCleanSession(true);
                        System.out.println("Connecting to broker:" + broker);
                        sampleClient.connect(connOpts);
                        System.out.println("Connected");

                        String topic = "friend_request";
                        String content = "hello king";
                        int qos = 2;
                        System.out.println("Publishing message:" + content);
                        MqttMessage message = new MqttMessage(content.getBytes());
                        message.setQos(qos);
                        sampleClient.publish(topic, message);
                        System.out.println("Message published");
                        Thread.currentThread().sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }
}
