package com.den.we.rpc;

import com.den.we.MessageRespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author fatKarin
 * @date 2019/11/1 16:16
 */
@FeignClient("message-relay")
public interface RelayRemoteService {

    @PostMapping("/relay/publishMessage")
    MessageRespResult publish(@RequestParam("topic")String topic, @RequestParam("payload")String payload);
}
