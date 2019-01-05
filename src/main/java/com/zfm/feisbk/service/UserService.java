package com.zfm.feisbk.service;

import com.zfm.feisbk.pojo.NormalResultDTO;
import com.zfm.feisbk.pojo.UserDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author JTR
 * @date 2018/12/24 10:25
 */
public interface UserService {
    UserDO login(String username, String password);

    UserDO verify(String username);

    UserDO addUser(UserDO user);

    Page<UserDO> getUserList(Pageable pageable);

    Long getUserSize();

    UserDO findUserById(Long id);
}
