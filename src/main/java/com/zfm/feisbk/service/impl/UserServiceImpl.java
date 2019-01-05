package com.zfm.feisbk.service.impl;

import com.zfm.feisbk.CustomerException;
import com.zfm.feisbk.dao.UserDao;
import com.zfm.feisbk.pojo.NormalResultDTO;
import com.zfm.feisbk.pojo.UserDO;
import com.zfm.feisbk.service.RecommendService;
import com.zfm.feisbk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author JTR
 * @date 2018/12/24 10:25
 */

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(RecommendService.class);
    @Autowired
    private UserDao userDao;

    /**
     * 登录
     * @param username
     * @param password
     */
    @Override
    public UserDO login(String username, String password) {
        UserDO user = null;
        try {
            user = userDao.findByUsername(username);
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomerException("no such user");
        }
        if (user == null) {
            throw new CustomerException("no such user");
        }
        try {
            if (password.equals(user.getPassword()) && username.equals(user.getUsername())) {
                return user;
            }
            else
                return null;
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomerException("登录失败");
        }

    }

    /**
     * 用户名不重复返回null 重复抛出异常
     * @param username
     */
    @Override
    public UserDO verify(String username) {
        UserDO user = null;
        try {
            user = userDao.findByUsername(username);
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomerException("not unique username");
        }
        if (user == null){
            return null;
        }
        if (username.equals(user.getUsername())) {
            throw new CustomerException("not unique username");
        }else {
            return null;
        }
    }

    /**
     * 添加用户
     * @param user
     */
    @Override
    public UserDO addUser(UserDO user) {
        try {
            userDao.save(user);
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomerException("modify failed");
        }
        return user;
    }

    /**
     * 获取用户列表
     * @param pageable
     */
    @Override
    public Page<UserDO> getUserList(Pageable pageable) {
        Page<UserDO> userList = null;
        try {
            userList = userDao.findAll(pageable);
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomerException("get user list failed");
        }
        return userList;
    }

    /**
     * 总用户数
     * @return
     */
    @Override
    public Long getUserSize() {
        Iterable<UserDO> all = null;
        Long size = 0L;
        try {
            all = userDao.findAll();
            for (UserDO user : all)
                size++;
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomerException("get user size failed");
        }
        return size;
    }

    /**
     * 通过id找到UserDo
     * @param id
     * @return
     */
    @Override
    public UserDO findUserById(Long id) {
        UserDO user = null;
        try {
            user = userDao.findOne(id);
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomerException("no such user");
        }
        if (user == null) {
            throw new CustomerException("no such user");
        }
        return user;
    }
}
