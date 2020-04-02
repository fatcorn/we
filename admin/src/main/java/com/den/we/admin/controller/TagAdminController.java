package com.den.we.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.den.we.AssertUtil;
import com.den.we.MessageCode;
import com.den.we.MessageResp;
import com.den.we.admin.service.IInterestTagService;
import com.den.we.admin.service.ITagTypeService;
import com.den.we.admin.vo.TagTableVo;
import com.den.we.admin.vo.TagTypeNameVo;
import com.den.we.entity.InterestTag;
import com.den.we.entity.TagType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
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

    @Resource
    private ITagTypeService tagTypeService;

    /**
     * 分页查询标签
     * @param   currentPage currentPage
     * @param   pageSize     pageSize
     * @return  MessageResp
     */
    @GetMapping("/findAllTags")
    public MessageResp<IPage<TagTableVo>> findAllTags(Integer currentPage, Integer pageSize) {
        AssertUtil.notNull(currentPage, MessageCode.REQUIRED_PARAMETER);
        AssertUtil.notNull(pageSize, MessageCode.REQUIRED_PARAMETER);
        return MessageResp.success4Data(tagService.findTagsByPage(currentPage,pageSize));
    }

    /**
     * 新增标签
     * @param interestTag   标签
     * @return MessageResp
     */
    @PostMapping("/addNewTag")
    public MessageResp addNewTag(InterestTag interestTag) {
        AssertUtil.fieldNotNull(interestTag,MessageCode.REQUIRED_PARAMETER);
        boolean result =  tagService.addNewTag(interestTag);
        AssertUtil.isTrue(result, MessageCode.ERROR);
        return MessageResp.success();
    }

    /**
     * 更新标签
     * @param interestTag   标签
     * @return MessageResp
     */
    @PostMapping("/updateTag")
    public MessageResp updateTag(InterestTag interestTag) {
        AssertUtil.fieldNotNull(interestTag,MessageCode.REQUIRED_PARAMETER);
        boolean result =  tagService.updateById(interestTag);
        AssertUtil.isTrue(result, MessageCode.ERROR);
        return MessageResp.success();
    }

    /**
     * 删除标签
     * @param id    标签id
     * @return MessageResp
     */
    @DeleteMapping("/deleteTag")
    public MessageResp deleteTag(Long id) {
        AssertUtil.notNull(id,MessageCode.REQUIRED_PARAMETER);
        boolean result =  tagService.removeById(id);
        AssertUtil.isTrue(result, MessageCode.ERROR);
        return MessageResp.success();
    }

    /**
     * 分页查询标签根类型
     * @param   currentPage   currentPage
     * @param   pageSize      pageSize
     * @return  MessageResp
     */
    @GetMapping("/root/types")
    public MessageResp<IPage<TagType>> findAllRootTypes(Integer currentPage, Integer pageSize) {
        AssertUtil.notNull(currentPage, MessageCode.REQUIRED_PARAMETER);
        AssertUtil.notNull(pageSize, MessageCode.REQUIRED_PARAMETER);
        return MessageResp.success4Data(tagTypeService.page(new Page<>(currentPage,pageSize)));
    }

    /**
     * 查询类型id与类型映射
     * @return  List
     */
    @GetMapping("/root/findAllRootType")
    public MessageResp<List<TagTypeNameVo>> findAllRootType() {
        return MessageResp.success4Data(tagTypeService.findNameIdMap());
    }

    /**
     * 新增根标签
     * @param tagType   根标签
     * @return  MessageResp
     */
    @PostMapping("/root/addNew")
    public MessageResp addRootType(TagType tagType) {
        AssertUtil.fieldNotNull(tagType,MessageCode.REQUIRED_PARAMETER);
        tagType.setCreateTime(new Date());
        boolean result =  tagTypeService.save(tagType);
        AssertUtil.isTrue(result, MessageCode.ERROR);
        return MessageResp.success();
    }

    /**
     * 更新根标签
     * @param tagType   根标签
     * @return  MessageResp
     */
    @PostMapping("/root/update")
    public MessageResp updateRootType(TagType tagType) {
        AssertUtil.fieldNotNull(tagType,MessageCode.REQUIRED_PARAMETER);
        boolean result =  tagTypeService.updateById(tagType);
        AssertUtil.isTrue(result, MessageCode.ERROR);
        return MessageResp.success();
    }

    /**
     * 删除根标签
     * @param id    标签id
     * @return  MessageResp
     */
    @DeleteMapping("/root/delete")
    public MessageResp deleteRootType(Long id) {
        AssertUtil.notNull(id,MessageCode.REQUIRED_PARAMETER);
        boolean result =  tagTypeService.removeById(id);
        AssertUtil.isTrue(result, MessageCode.ERROR);
        return MessageResp.success();
    }

}
