package com.den.we.service;

import org.springframework.stereotype.Service;

/**
 * 提供mqtt 发布服务
 * @author fatKarin
 * @date 2019/10/31 9:27
 */
@Service
public interface IPublishService {
    Object publish();
}
