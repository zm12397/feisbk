package com.zfm.feisbk.service;

import com.zfm.feisbk.pojo.UserDO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TestService {
    void addUser(UserDO user);
    void removeUser(Long id);
    void saveUser(UserDO user);
    UserDO getUser(Long id);
    UserDO getUser(String username);
    List<UserDO> getUsers();
}
