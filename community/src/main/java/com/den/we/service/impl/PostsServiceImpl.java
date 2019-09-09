package com.den.we.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.entity.Posts;
import com.den.we.mapper.PostsMapper;
import com.den.we.service.IPostsService;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author fatKarin
 * @date 2019/9/9 16:06
 */
@Service
public class PostsServiceImpl extends ServiceImpl<PostsMapper, Posts> implements IPostsService {


    @Override
    public boolean addNewOne(Long communityId, String title, String content, Long creatorId) {
        Posts posts = new Posts();
        posts.setCommunityId(communityId);
        posts.setTitle(title);
        posts.setContent(content);
        posts.setCreatorId(creatorId);
        posts.setCreateTime(Calendar.getInstance().getTime());

        int result = this.baseMapper.insert(posts);
        return result > 0;
    }
}
