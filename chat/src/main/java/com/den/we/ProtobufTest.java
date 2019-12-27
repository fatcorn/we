package com.den.we;

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
        chat.message.Builder messageBuilder =
                chat.message.newBuilder();
        // 设置Person的属性
        messageBuilder.setContent("hello world");
        messageBuilder.setSendFrom("你爸爸");
        messageBuilder.setSendTo("儿子");
        // 创建Person
        chat.message message = messageBuilder.build();
        // 序列化，byte[]可以被写到磁盘文件，或者通过网络发送出去。
        byte[] data = message.toByteArray();
        System.out.println("serialization end." + Arrays.toString(data));


        // 反序列化，byte[]可以读文件或者读取网络数据构建。
        System.out.println("deserialization begin.");
        try {
            chat.message deMessage = chat.message.parseFrom(data);
            System.out.println(deMessage.getContent());
            System.out.println(deMessage.getSendFrom());
            System.out.println(deMessage.getSendTo());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
