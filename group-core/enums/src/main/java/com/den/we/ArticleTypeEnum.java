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
public enum ArticleTypeEnum implements BaseEnum  {

    // 0
    TEXT("文字"),
    // 1
    PICTURE("图片"),
    // 2
    AUDIO("音频"),
    // 3
    VIDEO("视频");

    private String cnName;

    public ArticleTypeEnum getByIndex(Integer index) {
        return Arrays.stream(ArticleTypeEnum.values()).filter(e -> e.ordinal() == index).findFirst().get();
    }
}
