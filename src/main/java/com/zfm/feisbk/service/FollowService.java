package com.zfm.feisbk.service;

import com.zfm.feisbk.pojo.UserDO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FollowService {
    void addFollow(Long follower, Long followed);
    void deleteFollow(Long follower, Long followed);
    List<UserDO> getFollowers(Long userId);
    List<UserDO> getFolloweds(Long userId);
    List<UserDO> getAllUserByUsername(String username);
}
