package com.den.we.admin.config.webmvc;

import com.den.we.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;


/**
 * @author fatKarin
 * @date 2020/3/13 14:18
 */
public class IntegerCodeToEnumConverterFactory implements ConverterFactory<Integer, BaseEnum> {

    /**
     * 获取一个从 Integer 转化为 T 的转换器，T 是一个泛型，有多个实现
     *
     * @param targetType 转换后的类型
     * @return 返回一个转化器
     */
    @Override
    public <T extends BaseEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        return new IntegerToEnumConverter<>(targetType);
    }
}