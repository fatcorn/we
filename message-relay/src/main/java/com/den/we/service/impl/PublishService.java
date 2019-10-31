package com.den.we.service.impl;

import com.den.we.properties.MqttProperties;
import com.den.we.service.IPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author fatKarin
 * @date 2019/10/31 9:28
 */
@Service
public class PublishService implements IPublishService {

    @Resource
    private MqttProperties mqttProperties;

    @Override
    public Object publish() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Basic MWIzMGY0ZDQzYTY1ZjpNamt3TURjMk1EZzVNams0TXpNM01qRXpPRGM0TlRjNE16ZzJNREU0TmpNeE5qSQ==");
        Map<String, Object> params= new LinkedHashMap<>();
        //  也支持中文
        params.put("client_id", "relay");
        params.put("qos", 2);
        params.put("retain",true);
        params.put("topic", "friend_request");
        params.put("payload", "hello world!");

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, headers);

        ResponseEntity response = restTemplate.postForEntity(mqttProperties.getUrl(), requestEntity, Object.class);

        return response.getBody();
    }
}
