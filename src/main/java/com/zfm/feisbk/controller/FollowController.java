package com.zfm.feisbk.controller;

import com.zfm.feisbk.CustomerException;
import com.zfm.feisbk.pojo.NormalResultDTO;
import com.zfm.feisbk.pojo.UserDO;
import com.zfm.feisbk.service.FollowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/follow")
public class FollowController {
    private Logger logger = LoggerFactory.getLogger(FollowController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FollowService followService;

    @RequestMapping(path = "/follow.action", method = RequestMethod.POST)
    public NormalResultDTO follow(Long id) {
        NormalResultDTO result = new NormalResultDTO("9999","unknow error",null);
        Long userId = (Long) request.getSession().getAttribute("userid");

        try{
            followService.addFollow(userId,id);
            result.setCode("0000");
            result.setMessage("successful");
        }catch (CustomerException e){
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping(path = "/unfollow.action", method = RequestMethod.POST)
    public NormalResultDTO unfollow(Long id) {
        NormalResultDTO result = new NormalResultDTO("9999","unknow error",null);
        Long userId = (Long) request.getSession().getAttribute("userid");
        System.out.println("id = [" + id + "]");
        try{
            followService.deleteFollow(userId,id);
            result.setCode("0000");
            result.setMessage("successful");
        }catch (CustomerException e){
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping(path = "/get.action", method = RequestMethod.GET)
    public NormalResultDTO get(String username) {
        NormalResultDTO result = new NormalResultDTO("9999","unknow error",null);
        try{
            List<UserDO> userList = followService.getAllUserByUsername(username);
            if (userList==null||userList.size()==0){
                result.setMessage("noTheseUserByQuery");
                return result;
            }
            result.setData(userList);
            result.setCode("0000");
            result.setMessage("successful");
        }catch (CustomerException e){
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping(path = "/getbe.action", method = RequestMethod.GET)
    public NormalResultDTO getFollowers() {
        NormalResultDTO result = new NormalResultDTO("9999","unknow error",null);
        Long userId = (Long) request.getSession().getAttribute("userid");
        try{
            List<UserDO> userFollowers = followService.getFollowers(userId);
            result.setData(userFollowers);
            result.setCode("0000");
            result.setMessage("successful");
        }catch (CustomerException e){
            result.setMessage(e.getMessage());
        }
        return result;
    }
    @RequestMapping(path = "/getto.action", method = RequestMethod.GET)
    public NormalResultDTO getFolloweds() {
        NormalResultDTO result = new NormalResultDTO("9999","unknow error",null);
        Long userId = (Long) request.getSession().getAttribute("userid");
        try{
            result.setData(followService.getFolloweds(userId));
            result.setCode("0000");
            result.setMessage("successful");
        }catch (CustomerException e){
            result.setMessage(e.getMessage());
        }
        return result;
    }

}