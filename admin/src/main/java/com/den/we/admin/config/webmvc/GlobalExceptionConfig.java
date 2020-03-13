package com.den.we.admin.config.webmvc;

import com.den.we.MessageCode;
import com.den.we.MessageRespResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * @author fatKarin
 * @date 2019/9/24 9:37
 */
@ControllerAdvice
public class GlobalExceptionConfig {

    /**
     * 非法参数异常处理
     * 为空参数，无效参数
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public MessageRespResult illegalArgumentException(IllegalArgumentException exception) {
        MessageCode messageCode = MessageCode.convertToMessageCode(exception.getMessage());
        return MessageRespResult.error(messageCode);
    }
}
