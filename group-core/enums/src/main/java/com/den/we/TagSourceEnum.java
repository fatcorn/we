package com.den.we;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 标签来源常量
 *
 * @author fatKarin
 * @date 2019/9/6 10:44
 */
@AllArgsConstructor
@Getter
public enum TagSourceEnum implements BaseEnum {
    SYSTEM("系统"),
    USER("用户");

    private String cnName;

    @Override
    public TagSourceEnum getByIndex(Integer index) {
        return Arrays.stream(TagSourceEnum.values()).filter(e -> e.ordinal() == index).findFirst().get();
    }
}
