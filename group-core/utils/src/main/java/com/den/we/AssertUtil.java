package com.den.we;

import com.den.we.anotation.RequiredParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.lang.Nullable;

import java.lang.reflect.Field;

/**
 * 断言工具类
 * @author fatKarin
 * @date 2019/9/24 10:32
 */
public class AssertUtil {

    /**
     * 判断对象是否为空
     */
    public static void notNull(@Nullable Object object, MessageCode messageCode) {
        if(object == null) {
            throw new IllegalArgumentException(messageCode.getEnCode());
        }
    }

    /**
     * 检查前端传入对象@RequiredParam字段是否为空
     */
    public static void fieldNotNull(@Nullable Object object, MessageCode messageCode) {
        if(object == null) {
            throw new IllegalArgumentException(messageCode.getEnCode());
        }
        // 解析字段上是否有注解
        // ps：getDeclaredFields会返回类所有声明的字段，包括private、protected、public，但是不包括父类的
        // getFields:则会返回包括父类的所有的public字段，和getMethods()一样
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field field : fields){
            boolean fieldHasTag = field.isAnnotationPresent(RequiredParam.class);
            if(fieldHasTag){
                try {
                    field.setAccessible(true);
                    if(field.get(object) == null || "".equals(field.get(object))){
                        throw new IllegalArgumentException(messageCode.getEnCode());
                    }
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException(messageCode.getEnCode());
                }
            }
        }
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @param messageCode
     */
    public static void notEmpty(String str, MessageCode messageCode) {
        if(StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException(messageCode.getEnCode());
        }
    }

    /**
     * 判断true or false
     * @param result
     * @param messageCode
     */
    public static void isTrue(boolean result, MessageCode messageCode) {
        if(!result) {
            throw new IllegalArgumentException(messageCode.getEnCode());
        }
    }
}

