package com.den.we;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * 0, 1 公共常量
 *
 * @author fatKarin
 * @date 2019/9/6 10:44
 */
@AllArgsConstructor
@Getter
public enum CommonEnum implements BaseEnum  {
    DISABLE("否"),
    ENABLE("是");

    private String cnName;


    @Override
    public CommonEnum getByIndex(Integer index) {
        return Arrays.stream(CommonEnum.values()).filter(e -> e.ordinal() == index).findFirst().get();
    }
}
