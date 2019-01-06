package com.zfm.feisbk.controller;

import com.zfm.feisbk.CustomerException;
import com.zfm.feisbk.pojo.NormalResultDTO;
import com.zfm.feisbk.pojo.UserDO;
import com.zfm.feisbk.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JTR
 * @date 2018/12/24 9:57
 */

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param username
     * @param password
     * completed
     */
    @RequestMapping(path = "/login.action", method = RequestMethod.POST)
    public NormalResultDTO login(@RequestParam("username") String username,
                                 @RequestParam("password") String password, HttpServletRequest request) {
        NormalResultDTO result = new NormalResultDTO("9999","wrong username or password",null);
        try {
            UserDO user = userService.login(username, password);
            request.getSession().setAttribute("userid",user.getId());
            result.setCode("0000");
            result.setMessage("successful");
        }catch (CustomerException e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
