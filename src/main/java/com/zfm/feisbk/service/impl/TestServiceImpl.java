package com.zfm.feisbk.service.impl;

import com.zfm.feisbk.CustomerException;
import com.zfm.feisbk.dao.UserDao;
import com.zfm.feisbk.pojo.UserDO;
import com.zfm.feisbk.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestServiceImpl implements TestService {
    private Logger logger = LoggerFactory.getLogger(TestService.class);
    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(UserDO user) {
        try{
            userDao.save(user);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new CustomerException("用户添加失败");
        }

    }

    @Override
    public void removeUser(Long id) {

    }

    @Override
    public void saveUser(UserDO user) {

    }

    @Override
    public UserDO getUser(Long id) {
        return null;
    }

    @Override
    public UserDO getUser(String username) {
        return null;
    }

    @Override
    public List<UserDO> getUsers() {
        return null;
    }
}
