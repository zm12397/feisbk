package com.zfm.feisbk.service.impl;

import com.zfm.feisbk.CustomerException;
import com.zfm.feisbk.dao.UserDao;
import com.zfm.feisbk.pojo.TofollowDO;
import com.zfm.feisbk.pojo.UserDO;
import com.zfm.feisbk.service.RecommendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecommendServiceImpl implements RecommendService{
    private Logger logger = LoggerFactory.getLogger(RecommendService.class);
    @Autowired
    private UserDao userDao;

    @Override
    public Set<UserDO> getFriendSFriends(Long id) {
        UserDO user = null; //当前用户
        //获取当前用户
        try {
            user = userDao.findOne(id);
        }catch (Exception e){
            logger.info(e.getMessage());
            throw new CustomerException("找不到当前用户");
        }
        if(user == null){
            throw new CustomerException("找不到当前用户");
        }
//        logger.info("user:" + user.toString());
        Set<TofollowDO> followeds;                          //当前用户的关注关系集合
        Set<UserDO> followedUsers = new HashSet<UserDO>();  //当前用户的关注的人的集合
        Set<TofollowDO> friends = new HashSet<>();          //当前用户关注的人的关注关系集合
        Set<UserDO> friendUsers = new HashSet<UserDO>();    //当前用户的关注的人关注的人的集合
        //获取当前用户的关注关系集合
        try{
            followeds = user.getFolloweds();
        }catch (Exception e){
            logger.info(e.getMessage());
            throw new CustomerException("查询关注关系失败");
        }
        //如果当前用户没有关注别人，那就推荐不了
        if(followeds == null || followeds.isEmpty()){
            return new HashSet<>();
        }
//        logger.info(followeds.toString());

        //遍历当前用户的关注关系
        for(TofollowDO followed : followeds){
            UserDO endNode = null;      //关注关系中被关注的那个人，即关系的end节点
            try {
                endNode = followed.getEndNode();
            }catch (Exception e) {
                logger.error(e.getMessage());
                throw new CustomerException("查询关注的人失败");
            }
            if(endNode == null){
                throw new CustomerException("查询关注的人失败");
            }
            logger.info("endnode:" + endNode.toString());
            followedUsers.add(endNode);
            try{
                if(endNode.getFolloweds() == null ||  endNode.getFolloweds().isEmpty()){
                    continue;
                }
            }catch (Exception e){
                logger.error(e.getMessage());
                throw new CustomerException("查询关注的关注关系失败");
            }
            friends.addAll(endNode.getFolloweds());
        }
        for(TofollowDO friend : friends){
            UserDO endNode = null;
            try {
                endNode = friend.getEndNode();
            }catch (Exception e) {
                logger.error(e.getMessage());
                throw new CustomerException("查询关注的人关注的人失败");
            }
            if(endNode == null){
                throw new CustomerException("查询关注的人关注的人失败");
            }
            friendUsers.add(endNode);
        }
        friendUsers.removeAll(followedUsers);
        logger.info(friendUsers.toString());
        return friendUsers;
    }
}
