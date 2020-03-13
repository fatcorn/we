package com.den.we.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.den.we.AssertUtil;
import com.den.we.MessageCode;
import com.den.we.MessageRespResult;
import com.den.we.admin.service.IInterestTagService;
import com.den.we.entity.InterestTag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签管理
 * @author fatKarin
 * @date 2020/3/12 13:24
 */
@RequestMapping("/tagAdmin")
@RestController
public class TagAdminController {

    @Resource
    private IInterestTagService tagService;

    /**
     * 分页查询标签
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/findAllTags")
    public MessageRespResult<IPage<InterestTag>> findAllTags(Integer currentPage, Integer pageSize) {
        AssertUtil.notNull(currentPage, MessageCode.REQUIRED_PARAMETER);
        AssertUtil.notNull(pageSize, MessageCode.REQUIRED_PARAMETER);
        return MessageRespResult.success4Data(tagService.findTagsByPage(currentPage,pageSize));
    }

    /**
     * 新增标签
     * @param interestTag   标签
     * @return
     */
    @PostMapping("/addNewTag")
    public MessageRespResult addNewTag(InterestTag interestTag) {
        AssertUtil.fieldNotNull(interestTag,MessageCode.REQUIRED_PARAMETER);
        boolean result =  tagService.addNewTag(interestTag);
        AssertUtil.isTrue(result, MessageCode.ERROR);
        return MessageRespResult.success();
    }

    /**
     * 更新标签
     * @param interestTag   标签
     * @return
     */
    @PostMapping("/updateTag")
    public MessageRespResult updateTag(InterestTag interestTag) {
        AssertUtil.notNull(interestTag,MessageCode.REQUIRED_PARAMETER);
        boolean result =  tagService.updateById(interestTag);
        AssertUtil.isTrue(result, MessageCode.ERROR);
        return MessageRespResult.success();
    }

    /**
     * 删除标签
     * @param id    标签id
     * @return
     */
    @PostMapping("/deleteTag")
    public MessageRespResult deleteTag(Long id) {
        AssertUtil.notNull(id,MessageCode.REQUIRED_PARAMETER);
        boolean result =  tagService.removeById(id);
        AssertUtil.isTrue(result, MessageCode.ERROR);
        return MessageRespResult.success();
    }
}
