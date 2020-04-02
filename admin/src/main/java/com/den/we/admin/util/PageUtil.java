package com.den.we.admin.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author fatKarin
 * @date 2020/3/20 11:31
 */
public class PageUtil {

    public static void copyPageInfo(Page srcPage, Page targetPage) {
        targetPage.setSize(srcPage.getSize());
        targetPage.setCurrent((srcPage.getCurrent()));
        targetPage.setTotal(srcPage.getTotal());
        targetPage.setPages(srcPage.getPages());
    }
}
