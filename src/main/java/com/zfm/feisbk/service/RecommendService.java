package com.zfm.feisbk.service;

import com.zfm.feisbk.pojo.UserDO;

import java.util.List;
import java.util.Set;

public interface RecommendService {
    Set<UserDO> getFriendSFriends(Long id);
}
