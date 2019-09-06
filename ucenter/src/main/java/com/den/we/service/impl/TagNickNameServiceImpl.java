package com.den.we.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.entity.TagNickName;
import com.den.we.mapper.TagNickNameMapper;
import com.den.we.service.ITagNickNameService;
import org.springframework.stereotype.Service;


/**
 * 标签别名(TagNickName)表服务实现类
 *
 * @author makejava
 * @since 2019-09-06 11:39:01
 */
@Service
public class TagNickNameServiceImpl extends ServiceImpl<TagNickNameMapper, TagNickName> implements ITagNickNameService {

}