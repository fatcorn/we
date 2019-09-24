package com.den.we.config;

import com.den.we.MessageCode;
import com.den.we.MessageRespResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * ȫ���쳣����
 * @author fatKarin
 * @date 2019/9/24 9:37
 */
@ControllerAdvice
public class GlobalExceptionConfig {

    /**
     * �Ƿ������쳣����
     * Ϊ�ղ�������Ч����
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
