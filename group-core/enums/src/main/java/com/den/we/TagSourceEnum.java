package com.den.we;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 0, 1 公共常量
 *
 * @author fatKarin
 * @date 2019/9/6 10:44
 */
@AllArgsConstructor
@Getter
public enum TagSourceEnum {
    SYSTEM("系统"),
    USER("用户");

    private String cnName;
}
