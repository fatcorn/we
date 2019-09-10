package com.den.we.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.den.we.entity.Posts;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fatKarin
 * @since 2019-05-30
 */
public interface IPostsService extends IService<Posts> {

    boolean addNewOne(Long communityId, String title, String content, Long creatorId);

    boolean thumpUp(Long postsId);
}
