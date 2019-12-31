package com.den.we;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

/**
 * @author fatKarin
 * @date 2019/12/27 13:38
 */
public class ProtobufTest {

    public static void main(String[] args) {
        //  序列化
        // 创建Person的Builder
        chat.message.chat_message.Builder chatMessageBuilder =
                chat.message.chat_message.newBuilder();
        // 设置Person的属性
        chatMessageBuilder.setContent("hello world");
        chatMessageBuilder.setSendFrom("你爸爸");
        chatMessageBuilder.setSendTo("儿子");

        chat.message.Builder messageBuilder = chat.message.newBuilder();

        chat.message.Header.Builder headerBuilder = chat.message.Header.newBuilder();

        headerBuilder.setMessageType("chat_message");
        headerBuilder.setProtocolVersion(1.1f);

        messageBuilder.setHeader(headerBuilder.build());

        // 创建Person
        chat.message.chat_message chat_message = chatMessageBuilder.build();
        // 序列化，byte[]可以被写到磁盘文件，或者通过网络发送出去。
        byte[] data = chat_message.toByteArray();
        messageBuilder.setBody(ByteString.copyFrom(data));

        chat.message message = messageBuilder.build();
        System.out.println("serialization end." + message.toByteArray().length +  Arrays.toString(message.toByteArray()));


        // 反序列化，byte[]可以读文件或者读取网络数据构建。
        System.out.println("deserialization begin.");
        try {
            chat.message deMessage = chat.message.parseFrom(message.toByteArray());
            chat.message.chat_message chatMessage = chat.message.chat_message.parseFrom(deMessage.getBody());

            chat.message.getDescriptor().getNestedTypes().forEach(x -> {
                x.getFields().forEach(y -> {
                    System.out.println(x.getName() + ":" + y.getName());
                });

            });

            System.out.println(chatMessage.getContent());
            System.out.println(chatMessage.getSendFrom());
            System.out.println(chatMessage.getSendTo());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
