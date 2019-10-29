package com.den.we;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageBrokerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;



    @Test
    public void create(){
        //创建Exchange
        amqpAdmin.declareExchange( new DirectExchange( "exchange.direct") );
        amqpAdmin.declareExchange( new FanoutExchange( "exchange.fanout") );
        amqpAdmin.declareExchange( new TopicExchange( "exchange.topic") );
        amqpAdmin.declareExchange( new TopicExchange( "amp.topic") );

        //创建Queue
        amqpAdmin.declareQueue( new Queue( "direct.queue" , true ) );
        amqpAdmin.declareQueue( new Queue( "fanout.queue" , true ) );
        amqpAdmin.declareQueue( new Queue( "topic.queue" , true ) );

        //绑定Queue
        amqpAdmin.declareBinding( new Binding( "direct.queue" , Binding.DestinationType.QUEUE , "exchange.direct" , "direct.queue" , null ) );
        amqpAdmin.declareBinding( new Binding( "fanout.queue" , Binding.DestinationType.QUEUE , "exchange.direct" , "fanout.queue" , null ) );
        amqpAdmin.declareBinding( new Binding( "direct.queue" , Binding.DestinationType.QUEUE , "exchange.fanout" , "" , null ) );
        amqpAdmin.declareBinding( new Binding( "fanout.queue" , Binding.DestinationType.QUEUE , "exchange.fanout" , "" , null ) );
        amqpAdmin.declareBinding( new Binding( "direct.queue" , Binding.DestinationType.QUEUE , "exchange.topic" , "direct.#" , null ) );
        amqpAdmin.declareBinding( new Binding( "fanout.queue" , Binding.DestinationType.QUEUE , "exchange.topic" , "direct.*" , null ) );
        amqpAdmin.declareBinding( new Binding( "topic.queue" , Binding.DestinationType.QUEUE , "exchange.topic" , "mqtt.queue" , null ) );
        amqpAdmin.declareBinding( new Binding( "topic.queue" , Binding.DestinationType.QUEUE , "exchange.topic" , "mqtt.#" , null ) );
        amqpAdmin.declareBinding( new Binding( "topic.queue" , Binding.DestinationType.QUEUE , "exchange.topic" , "mqtt.*" , null ) );
        amqpAdmin.declareBinding( new Binding( "topic.queue" , Binding.DestinationType.QUEUE , "amq.topic" , "mqtt.*" , null ) );
        amqpAdmin.declareBinding( new Binding( "topic.queue" , Binding.DestinationType.QUEUE , "amq.topic" , "mqtt.#" , null ) );
    }

    @Test
    public void send2Direct() {
        Map<String , Object> map = new HashMap<>();
        map.put( "msg" , "这是一条点对点消息" );
        map.put( "data" , Arrays.asList("helloworld" , 123 , true) );

        rabbitTemplate.convertAndSend( "exchange.direct" , "direct.queue" , map );

    }

    @Test
    public void send2Topic() {
        Map<String , Object> map = new HashMap<>();
        map.put( "msg" , "这是一条广播消息" );
        map.put( "data" , Arrays.asList("topic消息" , 123 , true) );

        rabbitTemplate.convertAndSend( "exchange.fanout" , "", map );

    }

    @Test
    public void sendForMqtt() {
        Map<String , Object> map = new HashMap<>();
        map.put( "msg" , "这是一条mqtt消息" );
        map.put( "data" , Arrays.asList("topic消息" , 123 , true) );

        rabbitTemplate.convertAndSend( "amq.topic" , "mqtt.queue.myMq", map );

    }

    @Test
    public void receive() {
        Object o = rabbitTemplate.receiveAndConvert( "direct.queue" );
        o.getClass();
        System.out.println(o.getClass());
        System.out.println(o);
    }

    @Test
    public void addMqttMessage() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        // factory.setVirtualHost("");
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        Connection conn = null;
        Channel channel = null;
        try {
            conn = factory.newConnection();
            channel = conn.createChannel();

            byte[] messageBodyBytes = "{'text':'Hello, world!中文'}".getBytes();
            channel.basicPublish("amq.topic", "mqtt.test.aaa", null, messageBodyBytes);
        }finally {
            if (channel != null) {
                channel.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Test
    public void mqttClient() throws URISyntaxException {
        MQTT mqtt = new MQTT();
        mqtt.setHost("tcp://localhost:1883");
        final CallbackConnection connection = mqtt.callbackConnection();
        connection.listener(new Listener() {

            public void onDisconnected() {
                System.out.println("listen disConnect");
            }

            public void onConnected() {
                System.out.println("listent connect");
            }

            public void onPublish(UTF8Buffer topic, Buffer payload, Runnable ack) {
                System.out.println("listen publish");
                System.out.println(UTF8Buffer.decode(topic));
                System.out.println(UTF8Buffer.decode(Buffer.utf8(payload)));
                ack.run();
            }

            public void onFailure(Throwable value) {
                System.out.println("listen fail" + value.toString());
            }
        });
        connection.connect(new Callback<Void>() {
            public void onFailure(Throwable value) {
                System.out.println("connect fail");
            }

            // Once we connect..
            public void onSuccess(Void v) {
                // Subscribe to a topic
                Topic[] topics = { new Topic("amq/topic/mqtt/#", QoS.AT_LEAST_ONCE) };
                connection.subscribe(topics, new Callback<byte[]>() {
                    public void onSuccess(byte[] qoses) {
                        System.out.println("subscribe success");
                        System.out.println(new String(qoses).toString());
                    }

                    public void onFailure(Throwable value) {
                        System.out.println("subscribe fail");
                    }
                });

                // Send a message to a topic
                // connection.publish("foo", "Hello".getBytes(),
                // QoS.AT_LEAST_ONCE, false, new Callback<Void>() {
                // public void onSuccess(Void v) {
                // System.out.println("publish success");
                // }
                //
                // public void onFailure(Throwable value) {
                // System.out.println("publish fail");
                // }
                // });
            }
        });
        try {
            Thread.sleep(100000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // To disconnect..
        connection.disconnect(new Callback<Void>() {
            public void onSuccess(Void v) {
                System.out.println("disconnect success");
            }

            public void onFailure(Throwable value) {
                System.out.println("fail");
            }
        });
    }


}
