package com.den.we.admin.config.webmvc;

import com.den.we.AssertUtil;
import com.den.we.BaseEnum;
import com.den.we.MessageCode;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;


/**
 * 整型枚举转换
 * @author fatKarin
 * @date 2020/3/13 13:44
 */
public class IntegerToEnumConverter<T extends BaseEnum> implements Converter<Integer, T> {
    private T t;

    public IntegerToEnumConverter(Class<T> clazz) {
        try {
            t = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T convert(@Nullable Integer index) {
        t = (T) t.getByIndex(index);
        AssertUtil.notNull(t, MessageCode.REQUIRED_PARAMETER);
        return t;
    }
}