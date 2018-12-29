package com.zfm.feisbk.service.impl;


import com.zfm.feisbk.CustomerException;
import com.zfm.feisbk.dao.BlogDao;
import com.zfm.feisbk.dao.PublishDao;
import com.zfm.feisbk.dao.UserDao;
import com.zfm.feisbk.pojo.*;
import com.zfm.feisbk.service.DynamicService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: zhouchi
 * @Description:
 * @Date: Created in 4:13 PM 2018/12/23
 */
@Service
public class DynamicServiceImpl implements DynamicService{
    private Logger logger = LoggerFactory.getLogger(DynamicService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private PublishDao publishDao;


    @Override
    public NormalResultDTO postBlog(UserDO user, BlogDO blog) {
        NormalResultDTO result = new NormalResultDTO("9999", "unknow error", null);
        if (user == null) {
            throw new CustomerException("找不到当前用户");
        }
        try{
            blogDao.save(blog);

            PublishDO publishDO = new PublishDO(user,blog);
            publishDao.save(publishDO);

            result.setCode("0000");
            result.setMessage("successful");

        }catch (Exception e){
            logger.error(e.getMessage());
            throw new CustomerException("动态发布失败");
        }

        return result;
    }

    @Override
    public UserDO findById(Long id) {
        UserDO user = null;
        try {
            user = userDao.findOne(id);
        }catch (Exception e){
            logger.info(e.getMessage());
            throw new CustomerException("找不到当前用户");
        }
        if(user == null){
            throw new CustomerException("找不到当前用户");
        }

        return user;
    }



    @Override
    public List<Map<String, Object>> getBlogList(UserDO user) {

        Set<TofollowDO> followeds;//当前用户的关注关系集合
        List<Map<String, Object>> blogLists = new ArrayList<>();
        //获取当前用户的关注关系集合
        try{
            followeds = user.getFolloweds();
        }catch (Exception e){
            logger.info(e.getMessage());
            throw new CustomerException("查询关注关系失败");
        }

        //获取当前用户自己的动态
        Set<PublishDO> ownPublishes = user.getPublishes();
        BlogDO ownBlog = null;

        for (PublishDO publish: ownPublishes){
            Map<String, Object> blog = new HashMap<>();
            ownBlog = publish.getBlogDO();
            blog.put("name", user.getName());
            blog.put("userid", user.getId());
            blog.put("username", user.getUsername());
            blog.put("id", ownBlog.getId());
            blog.put("contentTest", ownBlog.getContentTest());
            blog.put("contentImage", ownBlog.getContentImage());
            blog.put("createTime", ownBlog.getCreateTime());
            blog.put("modifyTime", ownBlog.getModifyTime());
            blogLists.add(blog);
        }

        //如果当前用户没有关注别人，那就推荐不了
        if(followeds == null || followeds.isEmpty()){
            return blogLists;
        }
        for (TofollowDO followed: followeds){
            UserDO endNode = null;      //关注关系中被关注的那个人，即关系的end节点
            try {
                //注：这里不能自动的级联查询
                endNode = userDao.findOne(followed.getEndNode().getId());
            }catch (Exception e) {
                logger.error(e.getMessage());
                throw new CustomerException("查询关注的人失败");
            }
            if(endNode == null){
                throw new CustomerException("查询关注的人失败");
            }
            logger.info("endnode:" + endNode.toString());
            try{
                if(endNode.getFolloweds() == null ||  endNode.getFolloweds().isEmpty()){
                    continue;
                }
            }catch (Exception e){
                logger.error(e.getMessage());
                throw new CustomerException("查询关注的关注关系失败");
            }
            //当前关注的用户的动态
            Set<PublishDO> publishes = endNode.getPublishes();
            UserDO blogPublisher = null;
            BlogDO blogs = null;

            //如果关注的用户的动态为空，跳至下一个关注的用户
            if(publishes.isEmpty() || publishes == null){
                continue;
            }

            for (PublishDO publish: publishes){
                Map<String, Object> blog = new HashMap<>();
                blogs = publish.getBlogDO();
                blogPublisher = publish.getAuthor();
                blog.put("name", blogPublisher.getName());
                blog.put("userid", blogPublisher.getId());
                blog.put("username",blogPublisher.getUsername());
                blog.put("id", blogs.getId());
                blog.put("contentTest", blogs.getContentTest());
                blog.put("contentImage", blogs.getContentImage());
                blog.put("createTime", blogs.getCreateTime());
                blog.put("modifyTime", blogs.getModifyTime());

                blogLists.add(blog);
            }
        }

        logger.info(blogLists.toString());
        return blogLists;
    }

    @Override
    public List<BlogDO> getSpecificBlogList(UserDO user) {

        if(user == null){
            throw new CustomerException("找不到当前用户");
        }
        List<BlogDO> blogLists = new ArrayList<>();
        Set<PublishDO> publishes = user.getPublishes();
        BlogDO blog = null;
        for (PublishDO publish: publishes){
            blog = publish.getBlogDO();
            blogLists.add(blog);
        }

        Collections.sort(blogLists, new Comparator<BlogDO>() {
            @Override
            public int compare(BlogDO o1, BlogDO o2) {
                Long c1 = o1.getCreateTime();
                Long c2 = o2.getCreateTime();
                if(c1 > c2){
                    return -1;
                }else{
                    return 1;
                }
            }
        });

        return blogLists;
    }

}
