package com.zfm.feisbk.controller;

import com.zfm.feisbk.CustomerException;
import com.zfm.feisbk.pojo.NormalResultDTO;
import com.zfm.feisbk.pojo.UserDO;
import com.zfm.feisbk.service.UserService;
import com.zfm.feisbk.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JTR
 * @date 2018/12/24 11:42
 */

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    /**
     * 验证用户名是否重复 true表示不重复 false表示重复
     * @param username
     * completed
     */
    @RequestMapping(path = "/validate.action", method = RequestMethod.POST)
    public NormalResultDTO verify(@RequestParam("username") String username) {
        NormalResultDTO result = new NormalResultDTO("9999","not unique username",null);
        try {
            NormalResultDTO verify = userService.verify(username);
            if (verify.getCode().equals("0000")) {
                result.setCode("0000");
                result.setMessage("successful");
            }
        }catch (CustomerException e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 注册用户
     */
    @RequestMapping(path = "/register.action", method = RequestMethod.POST)
    public NormalResultDTO register(UserDO user) {
        NormalResultDTO result = new NormalResultDTO("9999","not unique username",null);
        try {
            NormalResultDTO verify = userService.verify(user.getName());
            if (verify.getCode().equals("0000")) {
                result.setCode("0000");
                result.setMessage("successful");
            }else {
                return result;
            }
            Long current = TimeUtils.getNow();
            user.setCreateTime(current);
            user.setModifyTime(current);
            Short s = 1;
            user.setState(s);
            NormalResultDTO addUser = userService.addUser(user);
            if (!addUser.getCode().equals("0000")) {
                result.setCode("9999");
                result.setMessage("register failed");
            }
        }catch (CustomerException e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
