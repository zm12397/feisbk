package com.zfm.feisbk.controller;

import com.zfm.feisbk.CustomerException;
import com.zfm.feisbk.pojo.NormalResultDTO;
import com.zfm.feisbk.pojo.UserDO;
import com.zfm.feisbk.service.RecommendService;
import com.zfm.feisbk.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
@RequestMapping("/recommend")
public class RecommendController {
    private Logger logger = LoggerFactory.getLogger(RecommendController.class);
    @Autowired
    private RecommendService recommendService;

    @RequestMapping(path = "/my.action", method = RequestMethod.GET)
    public NormalResultDTO add(HttpSession session) {
        NormalResultDTO result = new NormalResultDTO("9999","unknow error",null);
        UserDO user = null;
//        Long id = (Long) session.getAttribute("userid");
        Long id = 76l;
        try{
            Set<UserDO> friendSfriends = recommendService.getFriendSFriends(id);
            result.setCode("0000");
            result.setMessage("successful");
            result.setData(friendSfriends);
        }catch (CustomerException e){
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
