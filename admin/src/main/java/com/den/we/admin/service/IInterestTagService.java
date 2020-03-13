package com.den.we.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.den.we.entity.InterestTag;

import java.util.List;

/**
 * <p>
 *  标签服务类
 * </p>
 *
 * @author fatKarin
 * @since 2020-03-12
 */
public interface IInterestTagService extends IService<InterestTag> {

    /**
     * 标签分页查询
     * @param currentPage   当前页
     * @param pageSize      页面大小
     * @return
     */
    IPage<InterestTag> findTagsByPage(Integer currentPage, Integer pageSize);

    /**
     * 新增标签
     * @param interestTag  标签
     * @return
     */
    boolean addNewTag(InterestTag interestTag);
}
