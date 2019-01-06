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
            throw new CustomerException("用户名不存在");
        }
        if (user == null) {
            throw new CustomerException("用户名不存在");
        }
        if (password.equals(user.getPassword())) {
            if(user.getState() == 2){
                throw new CustomerException("用户已被注销");
            }
            return user;
        }else{
            throw new CustomerException("密码不正确");
        }



    }

    /**
     * 用户名不重复返回true 重复返回false
     * @param username
     */
    @Override
    public NormalResultDTO verify(String username) {
        NormalResultDTO result = new NormalResultDTO("9999", "unknow error", null);
        UserDO user = null;
        try {
            user = userDao.findByUsername(username);
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomerException("not unique username");
        }
        if (user == null){
            result.setCode("0000");
            result.setMessage("successful");
            return result;
        }
        if (username.equals(user.getUsername())) {
            throw new CustomerException("not unique username");
        }else {
            result.setCode("0000");
            result.setMessage("successful");
            return result;
        }
    }

    /**
     * 添加用户
     * @param user
     */
    @Override
    public NormalResultDTO addUser(UserDO user) {
        NormalResultDTO result = new NormalResultDTO("9999", "unknow error", null);
        try {
            userDao.save(user);
            result.setCode("0000");
            result.setMessage("successful");
        }catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomerException("modify failed");
        }
        return result;
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

    @Override
    public void updateUser(UserDO user) {
        System.out.println(user);
        UserDO userDO = null;
        try{
            userDO = userDao.findOne(user.getId());
        }catch (Exception e){
            logger.error("用户查询失败，数据库查询错误：" + e.getMessage());
            throw new CustomerException("用户查询失败");
        }
        if(user.getPassword() != null){
            userDO.setPassword(user.getPassword());
        }else if(user.getName() != null){
            userDO.setName(user.getName());
        }else if(user.getTel() != null){
            userDO.setTel(user.getTel());
        }else if(user.getEmail() != null){
            userDO.setEmail(user.getEmail());
        }else if(user.getAddress() != null){
            userDO.setAddress(user.getAddress());
        }else if(user.getSex() != null && user.getSex() != 0){

            userDO.setSex(user.getSex());
        }else if(user.getDescription() != null){
            userDO.setDescription(user.getDescription());
        }else if(user.getState() != null && user.getState() != 0){
            userDO.setState(user.getState());
        }
        System.out.println("user = [" + userDO + "]");
        try{
            userDao.save(userDO);
        }catch (Exception e) {
            logger.error("用户更新失败，数据库更新错误：" + e.getMessage());
            throw new CustomerException("用户更新失败");
        }
    }
}
