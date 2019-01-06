package com.zfm.feisbk.service.impl;

import com.zfm.feisbk.CustomerException;
import com.zfm.feisbk.dao.BeFollowDao;
import com.zfm.feisbk.dao.ToFollowDao;
import com.zfm.feisbk.dao.UserDao;
import com.zfm.feisbk.pojo.BefollowDO;
import com.zfm.feisbk.pojo.TofollowDO;
import com.zfm.feisbk.pojo.UserDO;
import com.zfm.feisbk.service.FollowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FollowServiceImpl implements FollowService {

    private Logger logger = LoggerFactory.getLogger(FollowService.class);

    @Autowired
    UserDao userDao;
    @Autowired
    BeFollowDao beFollowDao;
    @Autowired
    ToFollowDao toFollowDao;


    @Override
    public void addFollow(Long followerId, Long followedId) {
        UserDO follower = null;
        try{
            follower =  userDao.findOne(followerId);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new CustomerException("找不到当前用户");
        }
        if(follower == null){
            throw new CustomerException("找不到当前用户");
        }
        UserDO followed = null;
        try{
            followed =  userDao.findOne(followedId);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new CustomerException("找不到关注用户");
        }
        if(followed == null){
            throw new CustomerException("找不到关注用户");
        }
        try{
            TofollowDO tofollowDO = new TofollowDO(follower,followed);
            toFollowDao.save(tofollowDO);
            BefollowDO befollowDO = new BefollowDO(followed,follower);
            beFollowDao.save(befollowDO);
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new CustomerException("关注失败");
        }
    }

    @Override
    public void deleteFollow(Long followerId, Long followedId) {
        try{
            toFollowDao.deleteByStartAndEnd(followerId,followedId);
            beFollowDao.deleteByStartAndEnd(followedId,followerId);
        }catch (Exception e){
            logger.info(e.getMessage());
            throw new CustomerException("取消关注失败");
        }

    }

    @Override
    public List<UserDO> getFollowers(Long userId) {
        UserDO userDO = null;
        try{
            userDO =  userDao.findOne(userId);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new CustomerException("找不到当前用户");
        }
        if(userDO == null){
            throw new CustomerException("找不到当前用户");
        }
        List<UserDO> followers = new ArrayList<>();//当前用户的粉丝列表
        Set<BefollowDO> befollowDOSet= null;
        befollowDOSet = userDO.getFollowers();
        if (befollowDOSet==null){
            return followers;
        }
        List<BefollowDO> befollowDOList = new ArrayList<>(befollowDOSet);//当前用户的被关注关系列表
        //对当前用户的被关注关系按关注时间排序，时间近的在前面
        Collections.sort(befollowDOList, new Comparator<BefollowDO>() {
            @Override
            public int compare(BefollowDO o1, BefollowDO o2) {
                if (o1.getCreateTime()<o2.getCreateTime())
                    return 1;
                else if (o1.getCreateTime()>o2.getCreateTime())
                    return -1;
                else return 0;
            }
        });
        for (BefollowDO befollowDO : befollowDOList){
            UserDO endNode = null;      //被关注关系中的粉丝，即关系的尾节点
            try {
                //注：这里不能自动的级联查询
                endNode = userDao.findOne(befollowDO.getEndNode().getId());
            }catch (Exception e) {
                logger.error(e.getMessage());
                throw new CustomerException("查询粉丝失败");
            }
            if(endNode == null){
                throw new CustomerException("查询粉丝失败");
            }
            logger.info("endnode:" + endNode.toString());
            followers.add(endNode);
        }
        return followers;
    }

    @Override
    public List<UserDO> getFolloweds(Long userId) {
        UserDO userDO = null;
        try{
            userDO =  userDao.findOne(userId);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new CustomerException("找不到当前用户");
        }
        if(userDO == null){
            throw new CustomerException("找不到当前用户");
        }
        List<UserDO> followeds = new ArrayList<>();//当前用户的关注列表
        Set<TofollowDO> tofollowDOSet= null;
        tofollowDOSet = userDO.getFolloweds();
        if (tofollowDOSet==null){
            return followeds;
        }
        List<TofollowDO> tofollowDOList = new ArrayList<>(tofollowDOSet);
        //对当前用户的关注关系按关注时间排序，时间近的在前面
        Collections.sort(tofollowDOList, new Comparator<TofollowDO>() {
            @Override
            public int compare(TofollowDO o1, TofollowDO o2) {
                if (o1.getCreateTime()<o2.getCreateTime())
                    return 1;
                else if (o1.getCreateTime()>o2.getCreateTime())
                    return -1;
                else return 0;
            }
        });
        for (TofollowDO tofollowDO : tofollowDOList){
            UserDO endNode = null;      //关注关系中关注的人（非粉丝），即关系的尾节点
            try {
                //注：这里不能自动的级联查询
                endNode = userDao.findOne(tofollowDO.getEndNode().getId());
            }catch (Exception e) {
                logger.error(e.getMessage());
                throw new CustomerException("查询关注的人失败");
            }
            if(endNode == null){
                throw new CustomerException("查询关注的人失败");
            }
            logger.info("endnode:" + endNode.toString());
            followeds.add(endNode);
            logger.info("followeds:" + followeds.toString());
        }
        logger.info("followeds:" + followeds.toString());
        return followeds;
    }

    @Override
    public List<UserDO> getAllUserByUsername(String username) {
        List<UserDO> list = null;//模糊查询的用户列表
        try {
            String query  = ".*"+username+".*";//模糊查询正则
            list = new ArrayList<>(userDao.findUserDOByUsernameIsLike(query));
            logger.info(list.toString());
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new CustomerException("查询用户失败");
        }
        if (list==null)
            throw new CustomerException("查询用户失败");
        for (UserDO userDO : list){
            if (userDO.getFollowers()!=null)
                userDO.getFollowers().clear();
            if (userDO.getFolloweds()!=null)
                userDO.getFolloweds().clear();
        }
        return list;
    }

    @Override
    public List<UserDO> getUsers(String name) {
        List<UserDO> list = null;//模糊查询的用户列表
        try {
            String query  = ".*"+name+".*";//模糊查询正则
            list = new ArrayList<>(userDao.findByNameIsLike(query));
            logger.info(list.toString());
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new CustomerException("查询用户失败");
        }
        if (list==null)
            throw new CustomerException("查询用户失败");
        for (UserDO userDO : list){
            if (userDO.getFollowers()!=null)
                userDO.getFollowers().clear();
            if (userDO.getFolloweds()!=null)
                userDO.getFolloweds().clear();
        }
        return list;
    }
}
