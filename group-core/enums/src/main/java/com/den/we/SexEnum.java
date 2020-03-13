package com.den.we;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 0, 1 公共常量
 *
 * @author fatKarin
 * @date 2019/9/6 10:44
 */
@AllArgsConstructor
@Getter
public enum SexEnum implements BaseEnum{
    MALE("男"),
    FEMALE("女");

    private String cnName;

    @Override
    public SexEnum getByIndex(Integer index) {
        return Arrays.stream(SexEnum.values()).filter(e -> e.ordinal() == index).findFirst().get();
    }
}
