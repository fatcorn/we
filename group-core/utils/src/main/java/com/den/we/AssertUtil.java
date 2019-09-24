package com.den.we;

import org.apache.commons.lang.StringUtils;
import org.springframework.lang.Nullable;

/**
 * ���Թ�����
 * @author fatKarin
 * @date 2019/9/24 10:32
 */
public class AssertUtil {

    /**
     * �ж϶����Ƿ�Ϊ��
     */
    public static void notNull(@Nullable Object object, MessageCode messageCode) {
        if(object == null) {
            throw new IllegalArgumentException(messageCode.getEnCode());
        }
    }

    /**
     * �ж��ַ����Ƿ�Ϊ��
     * @param str
     * @param messageCode
     */
    public static void notEmpty(String str, MessageCode messageCode) {
        if(StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException(messageCode.getEnCode());
        }
    }

    /**
     * �ж�true or false
     * @param result
     * @param messageCode
     */
    public static void isTrue(boolean result, MessageCode messageCode) {
        if(!result) {
            throw new IllegalArgumentException(messageCode.getEnCode());
        }
    }
}

