package com.zfm.feisbk.service;

import com.zfm.feisbk.pojo.BlogDO;
import com.zfm.feisbk.pojo.NormalResultDTO;
import com.zfm.feisbk.pojo.UserDO;

import java.util.Set;

/**
 * @Author: zhouchi
 * @Description:
 * @Date: Created in 9:46 AM 2018/12/24
 */
public interface DynamicService {
    NormalResultDTO postBlog(UserDO user, BlogDO blog);
    UserDO findById(Long id);
    Set<BlogDO> getBlogList(UserDO user);
    Set<BlogDO> getSpecificBlogList(UserDO user);
}
